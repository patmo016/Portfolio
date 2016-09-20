#ifndef CONTAINER_H_
#define CONTAINER_H_

#include <stdio.h>

typedef struct containerrec *container;
typedef enum container_e {FLEX_ARRAY, RED_BLACK_TREE} container_t;

extern container container_new();
extern container container_rbt();
extern container container_flexarray();
extern void container_add(container c, char *word);
extern int container_search(container c, char *word);
extern void container_free(container c);
extern void container_print(container c, void p(char *str));

#endif    
