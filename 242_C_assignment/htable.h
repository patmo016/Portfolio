#ifndef HTABLE_H_
#define HTABLE_H_

#include <stdio.h>

typedef struct htablerec *htable;

extern void htable_free(htable h);
extern int htable_insert(htable h, char *str, char *type);
extern htable htable_new(int capacity);
extern void htable_print(htable h, void p( char *str));
extern int htable_search(htable h, char *str);

#endif
