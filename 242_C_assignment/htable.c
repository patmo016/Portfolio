#include <stdio.h>
#include <stdlib.h>
#include "htable.h"
#include "mylib.h"
#include <string.h>
#include "container.h"

/**
 * A hash table implementation using containers at each position.
 * Containers use chaining to store multiple items. These are either
 * of the type flexarray or red-black tree (RBT). When an item is added to a
 * container, it will either be appended to the underlying flexarray
 *
 * or RBT.
 * @author Molly Patterson
 * @author Nancy Wang
 * @author Alice Knowles
 */

struct htablerec {
    int capacity;
    int num_keys;
    int *frequencies;
    char **keys;
    container *containers;
};


/**
 * Creates a new empty htable.
 * Allocates memory to htable, containers and frequencies.
 * Initialises all positions in frequencies to 0.
 * Initialises all positions in containers to NULL.
 *
 *@param capacity is the size of htable we want to create.
 *@return my_hash_table is an empty htable.
 */
htable htable_new(int capacity){
    int i;
    /* printf("%d\n",capacity);*/
    htable my_hash_table = emalloc(sizeof *my_hash_table);
    my_hash_table->capacity= capacity;
    my_hash_table->num_keys = 0;
    my_hash_table->containers = emalloc(my_hash_table->capacity * sizeof my_hash_table->containers[0]);
    my_hash_table->frequencies = emalloc(my_hash_table->capacity * sizeof my_hash_table->frequencies[0]);
    
    for (i=0; i< capacity; i++){
        my_hash_table->frequencies[i] = 0;
        
        my_hash_table->containers[i] = NULL;
    } 
    return my_hash_table;

}

/**
 * Converts a given word into an int.
 *
 *@param *word is the word we wish to convert into an int.
 *@return result is the int resulting from converting the word.
 */
static unsigned int htable_word_to_int(char *word) {
    unsigned int result = 0;
    while (*word != '\0') {
        result = (*word++ + 31 * result);
    }
    return result;
}

/**
 * Inserts an item into the container of a given hash table. 
 *
 *@param h is the htable that we want to insert the item into.
 *@param str is the item to insert into h.
 *@param type determines whether a new container is a RBT or a flexarray.
 *@return 1 if item has been added to a newly created container.
 *@return 0 to finish.
 */
int htable_insert(htable h, char *str, char *type){
    unsigned int key;
    unsigned int index;
    int collisions=0; 
    /*the index to be inserted into*/
    key = htable_word_to_int (str);  
    index = key%h->capacity;
    /* printf(type);*/
    while(collisions < h->capacity){
            
        if (h->containers[index] == NULL){
            if (strcmp(type, "rbt") ==0){
            /*h->keys[index] = emalloc( strlen(str)+1 * sizeof h->keys[0]);
              strcpy(h->keys[index], str);*/
            h->containers[index]  = container_rbt();
            }else{
                h->containers[index] =container_flexarray();
            }                    
            container_add(h->containers[index], str);
            h->frequencies[index] += 1;
            h->num_keys ++;
            return 1;
        }
        
        /* else if (container_search(h->containers[index], str) != 0){    */  
            /* else if (strcmp(h->keys[index], str)==0){*/
        else{
            container_add(h->containers[index], str);
            collisions ++;
            h->frequencies[index]+=1;
            return h->frequencies[index];
        }
        /*    else{              
            index= (index+1)%h->capacity;
            collisions ++;
            }*/
    }
    return 0;       
}

/**
 * Scans a given htable for a specific item.
 *
 *@param h is the htable we want to search for our item in.
 *@param str is the item which we are trying to find in our htable.
 *@return the number of times the item is found or 0 if it is not found.
 */
int htable_search(htable h, char *str){
    int collisions =0;
    unsigned int key;
    unsigned index;
 

    key= htable_word_to_int(str);
    index = key%h->capacity;
    while (h->containers[index] != NULL  && collisions < h->capacity){
        /* if(strcmp(h->containers[index], str)!=0) {*/
        if (container_search(h->containers[index], str) != 0){
            /*printf("%d",h->frequencies[index]);*/
            return h->frequencies[index];
        }
        index = (1+index) % h->capacity;
        collisions++;
        
        /*proves its found and exits*/ 
        
        /* return h->frequencies[index];*/
    }
    return 0;
}


/**
 * Prints all non-empty containers from a given htable.
 *
 *@param h is the htable we want to print.
 *@param p takes a char (*str) which it uses to print.
 */
void htable_print(htable h, void p(char *str)){
    int i;
    /* printf("printer called");*/
    
    for (i=0; i<(h->capacity); i++){

        if (h->containers[i] != NULL){
            /* fprintf(stream, "%d  %s\n", h->frequencies[i], h->keys[i]);*/    
            printf("%d", i);
            container_print(h->containers[i] ,p );
            printf("\n");
        }
    }
}

   
/**
 * Deallocate memory given to the htable.
 *
 *@param h is the htable we want to set free.
 */
void htable_free(htable h){
    int i;
   
    for (i=0; i<h->capacity; i++){
        if(h->containers[i] != NULL){
            container_free(h->containers[i]);
        }
    }
    free(h->containers);
    free(h->frequencies);
    free(h);
                

}
