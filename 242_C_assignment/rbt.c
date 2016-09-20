#include <stdio.h>
#include <stdlib.h>
#include "rbt.h"
#include "mylib.h"
#include <string.h>

/*ex 16.1*/
/*typedef enum { RED, BLACK } rbt_colour;*/

/**
 * Implements a Red-Black Tree (RBT).
 *
 * @author Molly Patterson
 * @author Alice Knowles
 * @author Nancy Wang
 */

struct rbtnode {
    char *key;
    rbt_colour colour;
    rbt left;
    rbt right;
};

/*Macros that define whether the colour of the
  node is black and red respectively.*/
#define IS_BLACK(x) ((NULL == (x)) || (BLACK == (x)->colour))

#define IS_RED(x) ((NULL != (x)) && (RED == (x)->colour))


/**
 * Creates a new empty red-black tree.
 * @return my_rbt is the new red-black tree. 
 */

rbt rbt_new(){
    rbt my_rbt =NULL;
    return my_rbt;
}

/**
 * Finds the left-most child.
 * @param r is the red-black tree(rbt).
 * @return left_child(r->left).
 */

rbt left_child(rbt r){
    if (r->left == NULL){
        return r;
    }
    return left_child(r->left);
 

}

/**
 * Inserts a leaf node of type char into the current red-black tree(rbt).
 * @param r is the red-black tree(rbt).
 * @param *str is the new leaf node of type char that
 * we wish to insert into the current red-black tree(rbt).
 * @return r is the new red-black tree(rbt) after insertion.
 */
rbt rbt_insert(rbt r, char *str){

    if (r == NULL){
        r = emalloc(sizeof *r);
        r->key=NULL;
        r->left=NULL;
        r->right=NULL;
        r->colour=RED;
        r->key =  emalloc (strlen(str)+1 * sizeof r->key[0]);
        strcpy(r->key, str);
        r= rbt_fix(r);
        return r; 
    }
    else{
        
        /* if (strcmp(r->key, str) == 0){
            if(r->left == NULL){
                r->left= rbt_insert (r->left, str);
            }
            if(r->right== NULL){
                r->right = rbt_insert(r->right, str);
            } 
            else{
                r->left=rbt_insert(r->left,str);
                }*/
                
                /*   r=rbt_fix(r);*/
            /*is duplicates added here??*/
            
                /*  return r;   */ 
        
        if(strcmp(r->key, str) >= 0){
       
            r->left = rbt_insert(r->left, str);
        }else{
        
            r->right = rbt_insert(r->right,str);
        }
   
        r=rbt_fix(r);
        return r;
    }
    
} 



/**
 * Sorts the red-black tree by in-order traversal.
 * @param r is the red-black tree.
 * @param f takes a char, *str, to sort by in-order traversal.  
 */
void rbt_inorder(rbt b, void f(char *str)){
    if (b==NULL){
        return;
    }
    else{
        rbt_inorder(b->left, f);
        f(b->key);
        rbt_inorder(b->right,f);
    }
}

/**
 * Right-rotates the current red-black tree(rbt).
 * @param r is a red-black tree(rbt).
 * @return r is the right-rotated red-black tree(rbt).
 */
rbt right_rotate(rbt r){
    rbt temp;
   
    temp= r;
    r = temp->left;
    temp->left = r->right;
    r->right = temp;
    
    return r;
}


/**
 * Left-rotates the current red-black tree(rbt).
 * @param r is a red-black tree(rbt).
 * @return left-rotated red-black tree(rbt).
 */ 

rbt left_rotate(rbt r){
    rbt temp;
   
    temp= r;
    r = temp->right;
    temp->right = r->left;
    r->left = temp;
    
    return r;
}

/**
 * Function that ensures the root of the red-black tree(rbt) is always
 * black.
 * @param r is a red-black tree(rbt).
 * @return r is the adjusted red-black tree(rbt).
 */
rbt rbt_rootblack(rbt r){

    if(IS_RED(r)){
        r->colour = BLACK;
    }
    return r;
    
}

/**
 * Function that fixes the presence of any children/grandchildren pairs
 * being both red.
 * @param r is a red-black tree(rbt).
 * @return r is the fixed red-black tree(rbt) which follows all the colouring
 * rules.
 */

rbt rbt_fix(rbt r)
{
    if(IS_RED(r->left) && IS_RED(r->left->left)){
        if(IS_RED(r->right)){
            r->colour = RED;
            r->left->colour = BLACK;
            r->right->colour = BLACK;
        }else if(IS_BLACK(r->right)){
            r = right_rotate(r);
            r->colour = BLACK;  /*swap?*/
            r->right->colour = RED;
        }
    }else if(IS_RED(r->left) && IS_RED(r->left->right)){
        if(IS_RED(r->right)){
            r->colour = RED;
            r->left->colour = BLACK;
            r->right->colour = BLACK;
        }
        else if(IS_BLACK(r->right)){
            r->left = left_rotate(r->left);
            r = right_rotate(r);
            r->colour = BLACK;
            r->right->colour = RED;
        }
    }else if(IS_RED(r->right) && IS_RED(r->right->left)){
        if(IS_RED(r->left)){
            r->colour = RED;
            r->left->colour = BLACK;
            r->right->colour = BLACK;
        }else if(IS_BLACK(r->left)){
            r->right = right_rotate(r->right);
            r = left_rotate(r);
            r->colour = BLACK;
            r->left->colour = RED;
        }
    }else if(IS_RED(r->right) && IS_RED(r->right->right)){
        if(IS_RED(r->left)){
            r->colour = RED;
            r->left->colour = BLACK;
            r->right->colour = BLACK;
        }
        else if(IS_BLACK(r->left)){
            r = left_rotate(r);
            r->colour = BLACK;
            r->left->colour = RED;
        }
    }
   
    return r;
}
        
/**
 * Deletion function that deletes a leaf node of type char into the current 
 * red-black tree(rbt).
 * @param r is the red-black tree(rbt).
 * @param *str is the leaf node of type char that
 * we wish to delete from the current red-black tree(rbt).
 * @return r is the red-black tree(rbt) after the deletion.
 */
 
rbt rbt_delete(rbt r, char *str){
   
    rbt succ= r->right;
    /* printf("????");*/
    
    if(rbt_search(r, str) == 0){
        /*  printf("not in the bst");*/
        return r;
      
    }
    if (strcmp(r->key, str) == 0){
    
        /* printf(" attempting to delete ");*/
        /*printf(str);*/
        /* printf(b->key);*/
        if ((r->left==NULL)&& (r->right==NULL)){
            /* printf("children are null");*/
            
            /*free node??*/
            free(r->key);
            free(r); 
            r = NULL;
        }
        else if ((r->left!=NULL) && (r->right!=NULL)){
            
            succ = left_child(r->right);
            r->key= succ->key;
            printf("%s\n", succ->key);
            rbt_delete(r->right, succ->key);
        }
        else if ((r->left != NULL)&&(r->right == NULL)){
            free(r->key);
            free(r);
            r=r->left;
        }else if((r->right != NULL)&&(r->left == NULL)){
            free(r->key);
            free(r);
            r=r->right;
        }
        
       
    }
    else if (strcmp(r->key, str) > 0){
        r->left =rbt_delete(r->left, str);
    }
    else if (strcmp(r->key, str) < 0){
        r->right= rbt_delete(r->right, str);
    }
        
        
    
    /*  printf("end return");*/
    
    return r;
}   


        
/**
 * Sorts the red-black tree by pre-order traversal.
 * @param r is the red-black tree(rbt).
 * @param f takes two char's, *str and *c, to sort using pre-order
 * traversal.  
 */

void rbt_preorder(rbt r, void f(char *str)){ 
    if (r == NULL){
        return;
    }
   
    else{
    f(r->key); 
    rbt_preorder(r->left, f);
    rbt_preorder(r->right, f);
    }
    r = rbt_rootblack(r);
}


/**
 * Function that searchs a string, *str, in the red-black tree(rbt).
 * @param r is a red-black tree(rbt).
 * @param *str is the string we want to search for.
 * @return 0 if the tree is empty, 1 if the string is found.
 */
int rbt_search(rbt r, char *str){
    if (r == NULL){
        return 0;
    }
    else if (strcmp(r->key, str) == 0) {
        return 1;
        
    }else if(strcmp(r->key, str) > 0){
         return rbt_search(r->left, str);
        
    }else{
         return rbt_search(r->right, str);
    }
}

/**
 * Free all memory that has been allocated.
 * @param r is the current red-black tree(rbt).
 * @return r is the freed red-black tree(rbt).
 */
rbt rbt_free(rbt r){
    if(r == NULL){
        return r;
    }
     rbt_free(r->left);
     rbt_free(r->right);
    
     free(r->key);
     free(r);
     return r;

}

