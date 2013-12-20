#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(){

   double t0, tf;
   double tempo_gasto;
   t0 = clock();

			for(int i = 0; i < 1000; i++)
			
   tf = clock();
   tempo_gasto = ( (tf - t0) ) / CLOCKS_PER_SEC;
   printf("Tempo gasto: %lf s\n", tempo_gasto);

				system("pause");
}
