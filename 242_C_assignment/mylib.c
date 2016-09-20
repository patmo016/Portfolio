#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <ctype.h>
#include "mylib.h"

/**
 *Implementation of emalloc, erealloc and the getword function.
 * 
 * @author Molly Patterson
 * @author Alice Knowles
 * @author Nancy Wang
 */


/**
 * Allocates memory while checking errors.
 * @param s is the number of bytes of memory that needs to be allocated.  
 * @return result is the allocated memory.
 */
void *emalloc (size_t s){
    void *result = malloc(s);
    if (NULL== result){
        fprintf (stderr, "Memory allocation failed\n");
        exit(EXIT_FAILURE);
    }
    return result;
}

/**
 * Reallocates memory while checking errors.
 * @param *p is the current address of memory allocated.
 * @param s is the new size of memory to be reallocated.
 * @return result is the reallocated memory.
 * 
 */
void *erealloc(void *p, size_t s){
    void *result = realloc(p, s);
    if (NULL == result) {
        fprintf(stderr, "memory reallocation failed.\n");
        exit(EXIT_FAILURE);
    }
    return result;
}
 

/**
 * Reads in words from the input files.
 * @param *s is the character being read in each time.
 * @param limit is the maximum word length.
 * @param *stream is the file pointer.
 * @return the extracted words.
 */
int getword(char *s, int limit, FILE *stream) {
    int c;
    char *w = s;
    assert(limit > 0 && s != NULL && stream != NULL);
    /* skip to the start of the word */
    while (!isalnum(c = getc(stream)) && EOF != c)
        ;
    if (EOF == c) {
        return EOF;
    } else if (--limit > 0) { /* reduce limit by 1 to allow for the \0 */
        *w++ = tolower(c);
    }
    while (--limit > 0) {
        if (isalnum(c = getc(stream))) {
            *w++ = tolower(c);
        } else if ('\'' == c) {
            limit++;
        } else {
            break;
        }
    }
    *w = '\0';
    return w - s;
}
      

