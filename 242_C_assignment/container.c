#include <stdio.h>
#include <stdlib.h>
#include "htable.h"
#include "mylib.h"
#include "container.h"
#include "rbt.h"
#include "flexarray.h"
#include <string.h>


/**
 * Implements a container - a wrapper for a red-black tree or flexarray.
 *
 *@author Molly Patterson.
 *@author Nancy Wang.
 *@author Alice Knowles.
 */

struct containerrec {
    container_t type;
    void *contents;
};

/**
 * Creates a new container of type red-black tree (RBT).
 *
 *@return c is a new RBT wrapped inside a container 
 */


container container_rbt(){  
    /*emalloc container*/
    container c = emalloc(sizeof *c);
    c->type =RED_BLACK_TREE;
    c->contents = rbt_new();
    /* printf("addding new rbt?");*/
    return c;
}

/**
 * Creates a new container of type flexarray.
 *
 *@return c is a new flexarray wraped inside a container.
 */
container container_flexarray(){
    container c = emalloc(sizeof *c);
    c->type= FLEX_ARRAY;
    c->contents = flexarray_new();
    return c;
}



/**
 * Inserts an item into a given container.
 *
 *@param c is the container we want to insert our word into.
 *@param word is the item we want to insert into our container.
 */
void container_add(container c, char *word) {
    /* printf("inserting a word");*/
    if (c->type == RED_BLACK_TREE) {
        c->contents = rbt_insert(c->contents, word);
    } else {
      flexarray_append(c->contents, word);
    }
}

/**
 * Scans a given container for a specific item.
 *
 *@param c is the container we want to scan for our item.
 *@param word is the item we want to find in the given container.
 *@return rbt_search(c->contents, word) is 0 if item is not found,
 * or 1 if item is found.
 *@return is_present(c->contents, word) is 0 if if item is not found,
 * or 1 if item is found.
 */
int container_search(container c, char *word){
    /*printf("container search called");*/
    if (c->type == RED_BLACK_TREE) {
        return rbt_search(c->contents, word);
    } else {
       return is_present(c->contents, word);
    }
}

/**
 * Prints out contents of a container
 *
 *@param c is the container that we want to print.
 *@param p takes a char (str), used to print.
 */
void container_print(container c, void p(char *str)){
    
    if (c->type == RED_BLACK_TREE) {

        rbt_preorder(c->contents,p );
    } else {
        
        visit(c->contents, p);
    }
    
}


/**
 * Deallocates memory from container.
 *
 *@param c is the container that we want to set free.
 */

void container_free(container c){
    if (c->type == RED_BLACK_TREE) {
        rbt_free(c->contents);
    } else {
        flexarray_free(c->contents);
    }
   
    free(c);
}

 
