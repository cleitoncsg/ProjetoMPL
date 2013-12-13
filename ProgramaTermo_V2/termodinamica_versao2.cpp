#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#define LINHA 120
#define COLUNA 6
#define POSICAO_VR1 3
#define POSICAO_U2 2


float pressao, rc, calor, rp;
float vr2 = -999, vr1, vr3, vr4 = -9999;
float u1, u2, u3,u4;
float temperatura = -9999, temperatura_minima = -9999,
temperatura_maxima = -9999;
float pr2 = -9999, pr1,pr3, pr4 = -9999;
float h1, h2,h3,h4;
float entalpia = -9999, energia_interna = -9999,
volume_reduzido = -9999, energia_variavel = -9999;
FILE *arquivo;

void calculo_estado_1(){
     int marcador;
					bool achou = false;
					float  temporaria[LINHA][COLUNA];
     int i,j;
    
    arquivo = fopen("tabelaTermo.txt", "rt");
					
    //Laços para leitura do arquivo
				for(i = 0; i < LINHA; i++){
          for(j =0; j < 	COLUNA; j++){
																fscanf(arquivo, "%f",&temporaria[i][j]);
                if(temperatura == temporaria[i][j]){
																          marcador = i;
																          achou = true;
                }
                if(temperatura_minima == temporaria[i][j]){
																          marcador = i;
																          achou = true;
                }
                if(temperatura_maxima == temporaria[i][j]){
																          marcador = i;
																          achou = true;
                }
          }
										printf("\n");
				}
				
				system("cls");
				//Condição para imprimir o valor encontrado na tabela
				if( achou == true){
        printf("Estado 1:\n");
								printf("|%f K | %f kj/kg | %f | kj/kg | %f | %f kj/kg*K |\t",
								temporaria[marcador][0], temporaria[marcador][1], temporaria[marcador][2],
								temporaria[marcador][3], temporaria[marcador][4], temporaria[marcador][5]);

				}
				else{
         for(i = 0; i < LINHA; i++){
          for(j =0; j < 	COLUNA; j++){
                if(temperatura > temporaria[i][j]){
																          marcador = i;
																          achou = true;
                }
                else if(temperatura_minima > temporaria[i][j]){
																          marcador = i;
																          achou = true;
                }
                else if(temperatura_maxima > temporaria[i][j]){
																          marcador = i;
																          achou = true;
                }
                else{
																					break;
																}
										 }
										}
										
									 printf("Estado 1 (valor gerado por interpolacao):\n");
									 printf("|%f K | %f kj/kg | %f | kj/kg | %f | %f kj/kg*K |",
								  temporaria[marcador][0], temporaria[marcador][1], temporaria[marcador][2],
								  temporaria[marcador][3], temporaria[marcador][4], temporaria[marcador][5]);
				}

				pr1 = temporaria[marcador][2];
    u1 = temporaria[marcador][POSICAO_U2];
				vr1 = temporaria[marcador][POSICAO_VR1];
				h1 = temporaria[marcador][1];
				
				pr2 = rp*pr1;
				
				fclose(arquivo);
}

void calculo_estado_2(){
					int marcador;
					bool achouTr2 = false, achouVr2 = false;
					float  temporaria[LINHA][COLUNA];
     int i,j;
     
    	vr2 = vr1/rc;
     
    //Laços para leitura do arquivo
				for(i = 0; i < COLUNA; i++){
          for(j =0; j < 	COLUNA; j++){
                if(vr2 == temporaria[i][j]){
																          marcador = i;
																          achouVr2 = true;
                }
                if(pr2 == temporaria[i][j]){
																          marcador = i;
																          achouTr2 = true;
                }
          }
				}

				//Condição para imprimir o valor encontrado na tabela para Vr2
				if( achouVr2 == true){
        printf("Estado 2:\n");
	    			printf("|%f K | %f kj/kg | %f | kj/kg | %f | %f kj/kg*K |\t",
								temporaria[marcador][0], temporaria[marcador][1], temporaria[marcador][2],
								temporaria[marcador][3], temporaria[marcador][4], temporaria[marcador][5]);

				}
    //Condição para imprimir o valor encontrado na tabela para Tr2
			 else	if( achouTr2 == true){
        printf("Estado 2:\n");
	    			printf("|%f K | %f kj/kg | %f | kj/kg | %f | %f kj/kg*K |\t",
								temporaria[marcador][0], temporaria[marcador][1], temporaria[marcador][2],
								temporaria[marcador][3], temporaria[marcador][4], temporaria[marcador][5]);

				}
				else{
             printf("\nEstado 2 (valor gerado por interpolacao):\n");
			          for(i = 0; i < LINHA; i++){
               for(j =0; j < 	COLUNA; j++){
		                if(vr2  > temporaria[i][j]){
																		          marcador = i;
		                }
		                else if(pr2  > temporaria[i][j]){
																		          marcador = i;
		                }
		                else{
																							break;
																		}
																}
										     }
            			printf("|%f K | %f kj/kg | %f | kj/kg | %f | %f kj/kg*K |\t",
							        temporaria[marcador][0], temporaria[marcador][1], temporaria[marcador][2],
								       temporaria[marcador][3], temporaria[marcador][4], temporaria[marcador][5]);
			 }

				//Cálculo de Otto para estado 1
				u2 = temporaria[marcador][POSICAO_U2];
				h2 = temporaria[marcador][1];

}

void calculo_estado_3(){

					int marcador;
					bool achouVr3 = false, achouTemperaturaMaxima = false;
					float  temporaria[LINHA][COLUNA];
     int i,j;

    	vr3 = u2 + calor;

    //Laços para leitura do arquivo
				for(i = 0; i < LINHA; i++){
          for(j =0; j < 	COLUNA; j++){
                if(vr3 == temporaria[i][j]){
																          marcador = i;
																          achouTemperaturaMaxima = false;
																          achouVr3 = true;
                }
                if(temperatura_maxima == temporaria[i][j]){
																          marcador = i;
																          achouTemperaturaMaxima = true;
																          achouVr3 = false;
                }
          }
				}

				//Condição para imprimir o valor encontrado na tabela para Vr3
				if( achouVr3 == true){
        printf("Estado 3:\n");
	    			printf("|%f K | %f kj/kg | %f | kj/kg | %f | %f kj/kg*K |\t",
								temporaria[marcador][0], temporaria[marcador][1], temporaria[marcador][2],
								temporaria[marcador][3], temporaria[marcador][4], temporaria[marcador][5]);

				}
				         
				//Condição para imprimir o valor encontrado na tabela para temperatura máxima
				else if( achouTemperaturaMaxima == true){
        printf("Estado 3:\n");
	    			printf("|%f K | %f kj/kg | %f | kj/kg | %f | %f kj/kg*K |\t",
								temporaria[marcador][0], temporaria[marcador][1], temporaria[marcador][2],
								temporaria[marcador][3], temporaria[marcador][4], temporaria[marcador][5]);

				}
				else{
									printf("\nEstado 3 (valor gerado por interpolacao):\n");
         for(i = 0; i < LINHA; i++){
           for(j =0; j < 	COLUNA; j++){
              if(vr3  > temporaria[i][j]){
														          marcador = i;
              }
              else if(temperatura_maxima > temporaria[i][j]){
														          marcador = i;
              }
              else{
																			break;
														}
												}
						     }
	       			printf("|%f K | %f kj/kg | %f | kj/kg | %f | %f kj/kg*K |\t",
								   temporaria[marcador][0], temporaria[marcador][1], temporaria[marcador][2],
								   temporaria[marcador][3], temporaria[marcador][4], temporaria[marcador][5]);
				}
				     

   u3 = temporaria[marcador][POSICAO_U2];
   pr3 = temporaria[marcador][2];
  	h3 = temporaria[marcador][1];

}

void calculo_estado_4(){

					int marcador;
					bool achouVr4 = false, achouPr4 = false;
					float  temporaria[LINHA][COLUNA];
     int i,j;

    	vr4 = rc*vr3;
    	pr4 = pr3/rp;

    //Laços para leitura do arquivo
				for(i = 0; i < LINHA; i++){
          for(j =0; j < 	COLUNA; j++){
                if(vr4 == temporaria[i][j]){
																          marcador = i;
																          achouVr4 = true;
																          achouPr4 = false;
                }
																if(pr4 == temporaria[i][j]){
																          marcador = i;
																          achouPr4 = true;
																          achouVr4 = false;
                }
          }
				}

				//Condição para imprimir o valor encontrado na tabela
				if( achouVr4 == true){
				    for(j = 0; j < 	6 ; j++)
													printf("%f \t", temporaria[marcador][j]);

				}
				else if( achouPr4 == true){
				    for(j = 0; j < 	6 ; j++)
													printf("%f \t", temporaria[marcador][j]);

				}
				else{
         printf("\nEstado 4 (valor gerado por interpolacao):\n");
         for(i = 0; i < LINHA; i++){
           for(j =0; j < 	COLUNA; j++){
              if(vr4  > temporaria[i][j]){
														          marcador = i;
              }
              else if(pr4 > temporaria[i][j]){
														          marcador = i;
              }
              else{
																			break;
														}
												}
						     }
		      			printf("|%f K | %f kj/kg | %f | kj/kg | %f | %f kj/kg*K |\t",
								   temporaria[marcador][0], temporaria[marcador][1], temporaria[marcador][2],
								   temporaria[marcador][3], temporaria[marcador][4], temporaria[marcador][5]);
				}
				         
    u4 = temporaria[marcador][POSICAO_U2];
   	h4 = temporaria[marcador][1];

}


void calculosFinaisOpcao1();
void calculosFinaisOpcao2();
void menu();
void cabecalho();
void menu_opcao_3();
void sair();

int main(){
				
				int opcao;
				cabecalho();
				
				for(;;){
	    menu();
					scanf("%d",&opcao);

					if(opcao == 1){
							printf("Digite a temperatura\n");
							scanf("%f",&temperatura);

							printf("Digite o rc\n");
							scanf("%f",&rc);

							printf("Digite o calor\n");
							scanf("%f",&calor);

							system("color F0");
							calculo_estado_1();
					  calculo_estado_2();
					  calculo_estado_3();
					  calculo_estado_4();
					  calculosFinaisOpcao1();
	    }
					else if(opcao == 2){
	   	  printf("Digite a temperatura minima\n");
							scanf("%f",&temperatura_minima);

							printf("Digite a temperatura maxima\n");
							scanf("%f",&temperatura_maxima);

							printf("Digite o rp\n");
							scanf("%f",&rp);
							
       system("color F0");
							calculo_estado_1();
					  calculo_estado_2();
					  calculo_estado_3();
					  calculo_estado_4();
							calculosFinaisOpcao2();
					}
					else if(opcao == 3){

										int opcaoEntrada;
										printf("Digite as opcoes do menu abaixo:\n");
										printf("1 = Temperatura (K)\n");
										printf("2 = Entalpia (kj/kg)\n");
										printf("3 = Pressao reduziada\n");
										printf("4 = Energia interna (kj/kg)\n");
										printf("5 = Volume reduzido\n");
										printf("6 = Energia variavel (kj/kg*K)\n");

										scanf("%d",&opcaoEntrada);
										switch(opcaoEntrada){
													case 1:
																		printf("Vc selecionou a temperatura. Digite a temperatura, por favor:\n");
																		scanf("%f",&temperatura);
																		printf("Pressione qualquer tecla para procurar na tabela\n");
															   break;

													case 2:
	  							        printf("Vc selecionou a entalpia. Digite a entalpia, por favor:\n");
															   scanf("%f",&entalpia);
																		printf("Pressione qualquer tecla para procurar na tabela\n");

															   break;
             case 3:
																		printf("Vc selecionou a pressao reduzida. Digite a pressao, por favor:\n");
																		scanf("%f",&pressao);
																		printf("Pressione qualquer tecla para procurar na tabela\n");
															   break;
             case 4:
																		printf("Vc selecionou a energia interna. Digite a energia interna, por favor:\n");
																		scanf("%f",&energia_interna);
																		printf("Pressione qualquer tecla para procurar na tabela\n");
															   break;
              case 5:
																		printf("Vc selecionou o volume reduzido. Digite o volume reduzido, por favor:\n");
																		scanf("%f",&volume_reduzido);
																		printf("Pressione qualquer tecla para procurar na tabela\n");
															   break;
               case 6:
																		printf("Vc selecionou a energia variavel. Digite a energia variavel, por favor:\n");
																		scanf("%f",&energia_variavel);
																		printf("Pressione qualquer tecla para procurar na tabela\n");
															   break;
															default:
															   printf("Nenhuma opcao valida selecionada\n");

										}
					     menu_opcao_3();
					}
					
					else if(opcao == 4 ){
										sair();
					}
					else
					    printf("Voce digitou uma opcao invalida\n");

					printf("\nPressione qualquer tecla para voltar ao menu principal\n");
    	getch();
    	system("cls");

    }

				printf("\n");
				system("pause");
}

void calculosFinaisOpcao1(){
					float trabalho_liquido = calor - ( u4 - u1);
					float eficiencia = trabalho_liquido/calor;
					printf("\nEficiencia = %f J, trabalho liquido = %f J\n",eficiencia, trabalho_liquido);
}

void calculosFinaisOpcao2(){
					float trabalho_saida = h3 - h4;
					float trabalho_entrada = h2 - h1;
					float trabalho_liquido = trabalho_saida - trabalho_entrada;
					float eficiencia = trabalho_liquido/(h3 - h2);
					
					printf(" Trabalho de entrada = %f\n", trabalho_entrada);
					printf(" Trabalho de saida   = %f\n", trabalho_saida);
					printf(" Trabalho liquido = %f\n", trabalho_liquido);
					printf(" Eficiencia = %f\n", eficiencia);
}

void cabecalho(){
     system("color 2F");
					printf("\t______________________________________________________________\n");
					printf("\t\t\tUniversidade de Brasilia - FGA\n");
					printf("\t\tAluna: Fernanda Clemente Araujo Matricula: 11/0011376\n");
					printf("\t\tAluna: Raiane Rocha Fialho      Matricula: 11/0019504\n");
					printf("\t______________________________________________________________\n");
					
					printf("Pressione qualquer tecla para iniciar o programa, por favor:");

					getch();
					system("cls");
}

void menu(){
					system("color F2");
					printf("\t\t-__-__-__-__-__-__-__-__-__-__-__-___-___-___-___-_\n");
					printf("\t\t\tDigite 1 para entrar no ciclo de Otto\n");
					printf("\t\t\tDigite 2 para entrar no ciclo de Brayton\n");
					printf("\t\t\tDigite 3 buscar os dados na tabela\n");
					printf("\t\t\tDigite 4 sair do programa\n");
					printf("\t\t_-__-__-__-__-__-__-__-__-__-__-__-___-___-___-___-_\n");

}


void menu_opcao_3(){
     int marcador;
					bool achou = false;
					float  temporaria[LINHA][COLUNA];
     int i,j;

					  arquivo = fopen("tabelaTermo.txt", "rt");
    //Laços para leitura do arquivo
				for(i = 0; i < LINHA; i++){
          for(j =0; j < 	COLUNA; j++){
             			fscanf(arquivo, "%f",&temporaria[i][j]);
                if(temperatura == temporaria[i][j]){
																          marcador = i;
																          achou = true;
                }
                if(pressao == temporaria[i][j]){
																          marcador = i;
																          achou = true;
                }
                if(entalpia == temporaria[i][j]){
																          marcador = i;
																          achou = true;
                }
                if(energia_interna == temporaria[i][j]){
																          marcador = i;
																          achou = true;
                }
                if(volume_reduzido == temporaria[i][j]){
																          marcador = i;
																          achou = true;
                }
                if(energia_variavel == temporaria[i][j]){
																          marcador = i;
																          achou = true;
                }
          }
										printf("\n");
				}

				system("color F5");
				system("cls");
				//Condição para imprimir o valor encontrado na tabela
				if( achou == true){
								printf("Valores encontrados com sucesso na tabela:\n\n");
			    	printf("|%f K | %f kj/kg | %f | kj/kg | %f | %f kj/kg*K |\t",
        temporaria[marcador][0], temporaria[marcador][1], temporaria[marcador][2],
	       temporaria[marcador][3], temporaria[marcador][4], temporaria[marcador][5]);
				}
				else{
         printf("\nValores gerados por interpolacao:\n");
			          for(i = 0; i < LINHA; i++){
               for(j =0; j < 	COLUNA; j++){
		               if(temperatura > temporaria[i][j]){
																          marcador = i;
                }
                if(pressao > temporaria[i][j]){
																          marcador = i;
                }
                if(entalpia > temporaria[i][j]){
																          marcador = i;
                }
                if(energia_interna > temporaria[i][j]){
																          marcador = i;
                }
                if(volume_reduzido > temporaria[i][j]){
																          marcador = i;
                }
                if(energia_variavel > temporaria[i][j]){
																          marcador = i;
                }
		                else{
																							break;
																		}
																}
										     }
            			printf("|%f K | %f kj/kg | %f | kj/kg | %f | %f kj/kg*K |\t",
							        temporaria[marcador][0], temporaria[marcador][1], temporaria[marcador][2],
								       temporaria[marcador][3], temporaria[marcador][4], temporaria[marcador][5]);
				}

}

void sair(){
         int i, j = 0;
         
									system("cls");
         for(i = 0; i < 20; i++){
                 system("color ae");
                 while(j < 100){
                         printf("%d\t",j++); system("color ea"); }
         }

        system("color 1f");
        system("cls");
        printf("\n  ...    ........   ......   .   ........   ......   .......     .......  \n");
								printf("\n.     .  .      .   .     .  .   .          .    .   .      .    .     .  \n");
								printf("\n.     .  .    .     .    .   .   .          .    .   .       .   .     .  \n");
								printf("\n  ...    . .        .  .     .   .  .....   ......   .        .  .......  \n");
								printf("\n         .    .     . .      .   .  .   .   .    .   .        .           \n");
								printf("\n         .      .   .   .    .   .      .   .    .   .       .            \n");
								printf("\n         .........  .     .  .   ........   .    .   ........             \n\n\n\n");

        printf("\t\t\tpor ter utilizado nosso programa\n");
        getch();
        exit(0);
}
