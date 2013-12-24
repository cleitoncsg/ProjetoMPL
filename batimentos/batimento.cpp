#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <conio.h>
#include <windows.h>
#define FREQUENCIA 200
#define TEMPO 50

int main(){

   FILE *arquivo;
   double tempo_0, tempo_f;
   double tempo_gasto;
   double leitura_arquivo;
			int controle_tempo = 1;
			
   tempo_0 = clock();
   
   arquivo = fopen("1HoraMPL.txt", "rt");

			for(; !(tempo_gasto >= 20) ; ){
     tempo_f = clock();
     tempo_gasto = (tempo_f - tempo_0) / CLOCKS_PER_SEC;
     
     if(tempo_gasto == controle_tempo || tempo_gasto > controle_tempo-0.1){
        Beep(FREQUENCIA,TEMPO);
								controle_tempo++;
					   fscanf(arquivo, "%lf\n",&leitura_arquivo);
        printf("Preco %d = %lf\n",controle_tempo-1, leitura_arquivo);
					}
     
			}
			
   printf("\nTempo gasto para leitura: %lf s\n", tempo_gasto);

				system("pause");
}
