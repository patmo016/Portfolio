#include <stdio.h>
#include <stdlib.h>
#include "mylib.h"
#include "flexarray.h"
#include "string.h"

/**
* Implements a flexarray.
*
*@author Molly Patterson.
*@author Nancy Wang.
*@author Alice Knowles.
*/

struct flexarrayrec {
    int capacity;
    int itemcount;
    char **items;
};



/**
 * Uses quicksort to sort items.
 *
 *@param array are the items in the flexarray we want to sort.
 *@param len is the number of items in that array.
 */
static void quicksort (char **array, int len){
    int i, j;
    char *temp;
    char *pivot;
    
    if (len<2){
        return;
    }
        pivot = array[0];
        i=-1;
        j= len;
      
        for(;;){
            do{
                i++;
            }while(strcmp(array[i],pivot)<0);
            do{
                j--;
            } while(strcmp(array[j],pivot)>0);
              
            if(i < j){
                temp=array[i];
                array[i]= array[j];
                array[j]=temp;
            }else{
                break;
            }
        }
        
        quicksort(array,j+1);
       
        quicksort(array+j+1, len-j-1);
    
}
            
    

/**
 * Scans a flexarray for an item.
 *
 *@param f is the flexarray we want to scan for our item.
 *@param item is the item we are trying to find in our flexarray
 *@return 1 if the item is present in the given flexarray, or 0 if
 * the item can not be found.
 */
int is_present (flexarray f, char *item){
  
    int i =0;
    for (i=0; i< f->itemcount; i++){
        if (strcmp (f->items[i],item) == 0){
           
            return 1;
        }
    }
    return 0;

}

/**
 * Iterates through each item in the flexarray and prints it.
 *
 *@param f is the flexarray that we want to iterate through.
 *@param p takes a char (str), and uses it to print. 
 */
void visit(flexarray f, void p(char *str)){
    int i;
    if (f->itemcount ==0){
        return;
    }
    for (i=0; i<f->itemcount; i++){
        p(f->items[i]);
    }
} 

/**
 * Creates a new flexarray.
 *
 *@return result is the newly created flexarray.
 */
flexarray flexarray_new() {
    int i=0;
    flexarray result = emalloc(sizeof *result);
    result->capacity = 2;
    result->itemcount = 0;
    result->items = emalloc(result->capacity * sizeof result->items[0]);
    /*h->keys[index] = emalloc( strlen(str)+1 * sizeof h->keys[0])*/
    /*   printf("new flexarray");*/
    for (i=0; i<result->capacity; i++){
        result->items[i] = NULL;
    }
    return result;
}

/**
 * Appends items to a given flexarray.
 *
 *@param f is the flexarray we want to item to be appended to.
 *@param item is the word we want to apend to the flexarray.
 */
void flexarray_append(flexarray f, char *item) {
   
    if (f->itemcount == f->capacity) {
        f->capacity += f-> capacity;
        f-> items = erealloc (f->items, f->capacity * sizeof f->items[0]);
    }
    
    f->items[f->itemcount] = emalloc((strlen(item) +1) * sizeof f->items[0][0]);
    strcpy(f->items[f->itemcount],item);
    f->itemcount++;
 
}

/**
 * Prints out each item in the flexarray.
 *
 *@param f is the flexrray that needs to be printed out.
 */
void flexarray_print(flexarray f) {
    
    int i;
    for (i=0; i<(f->itemcount); i++){
     
        printf("%s\n", f->items[i]);
    }
}

/**
 * Sorts the given flexarray.
 *
 *@param f is the flexarray which needs to be sorted
 */
void flexarray_sort(flexarray f) {
    quicksort(f->items, f->itemcount);
}

/**
 * Deallocate memory from flexarray
 *
 *@param f is the flexarray that we want to set free.
 **/
void flexarray_free(flexarray f) {
  
    int i=0;
    for (i=0; i < f->itemcount; i++){
            free(f->items[i]);
        
    }
    free(f->items);
    free(f);
}
