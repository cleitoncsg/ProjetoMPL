#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <windows.h>

int main(){

   double t0, tf;
   double tempo_gasto;
   t0 = clock();

			for(; /**/; ){
     tf = clock();
     tempo_gasto = ( (tf - t0) ) / CLOCKS_PER_SEC;
					sleep(1,1);
					printf("\a");
     if(tempo_gasto == 5) break;
			}
			
   printf("Tempo gasto: %lf s\n", tempo_gasto);
   

				system("pause");
}
