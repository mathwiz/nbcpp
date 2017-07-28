#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int runargs(int argc, char**argv) {
    int i;

    printf("Arguments:\n");
    for (i = 0; i < argc; i++) {
        printf("%i: %s\n", i, argv[i]);
    }
    
    return 0;
}
