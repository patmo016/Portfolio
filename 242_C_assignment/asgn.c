#include <stdio.h>
#include <stdlib.h>
#include "rbt.h"
#include "container.h"
#include "mylib.h"
#include "flexarray.h"
#include "htable.h"
#include <getopt.h>
#include <time.h>

/**
 * Takes in a file.
 * Uses file to create hash table.
 * Checks if input exists in the file.
 *
 *@author Molly Patterson
 *@author Alice Knowels
 *@author Nancy Wang
 */

/**
 *Prints out the colour of the nodes in an rbt.
 *
 *@param s is the string we want to print.
 *@param c is the colour of the string.
 */

void print_key(char *s, rbt_colour c) {
    if (c == BLACK){
        printf("black: %s\n", s);
    } else if (c == RED){
        printf("red: %s\n", s);
    }
}

/**
 * Used as a print method passed through to htable_print
 *
 *@param s is the string to print out.
 */
void printer(char *s){
    fprintf(stdout, " %s", s);
}


/*main*/
int main(int argc, char *argv[]) {
    FILE *file;
    int unknownwords=0;
    htable h;
    char word[256];
    int capacity =3877 ;
    char *type = "flexarray";
    int timeflag = 0;
    int printflag = 0;
    clock_t instart, inend, searchstart, searchstop;
    const char *optstring = "rs:pih";
    char option;

    /*command line options*/
    while ((option = getopt(argc, argv, optstring)) != EOF) {
        switch (option) {
            case 'r':
                type = "rbt";
                break;
            case 's':
                capacity = atoi(optarg);
              
               
                /* the argument after the -b is available
                   in the global variable ’optarg’ */
                break;
            case 'p':
                printflag =1;
                /* return EXIT_SUCCESS;*/   
                break;
            case 'i':
                timeflag=1;
                /* hashtime(capacity,type,file);*/
                /* return EXIT_SUCCESS;*/
                break;
            case 'h':
                
            default:
                /* print help message"*/
                
                fprintf(stderr, "HELP: Enter a number of words and push enter after each.\n" "Push control d to finish entering words. \n""The output will produce the words you entered with the type of container they are held in.\n");
                
                return EXIT_SUCCESS;
                break;        
        }

    }
    /*reads in a file from the command line*/
    file= fopen(argv[optind], "r");

  
    h = htable_new(capacity);
    

    instart = clock();


    while (getword(word, sizeof word, file) != EOF){
        htable_insert(h, word, type);
    }
    inend = clock();
    searchstart=clock();
    
    if (printflag == 0){
        /*reads words from stdin*/    
        while (getword(word, sizeof word, stdin) != EOF) {
            if( htable_search(h, word) == 0){
                fprintf(stdout,"%s\n", word);
                unknownwords++;
            }
        }
    }

    /* if (timeflag !=0){*/
    searchstop=clock();
    if (timeflag!=0){
        fprintf(stderr, "Insert Time: %f\n", (inend-instart) / (double)
                CLOCKS_PER_SEC);
        fprintf(stderr, "Search Time: %f\n", (searchstop-searchstart) /
                (double)CLOCKS_PER_SEC);
        /* container_print(my_container, printer);*/
        printf("Unknown Words:%d \n", unknownwords);
    }
    /* if flag enabled: print*/
    if (printflag != 0){
        htable_print(h, printer);
    }
            
    htable_free(h);
   
 

    fclose(file);

    return EXIT_SUCCESS;
      
  
}
/*end main*/
