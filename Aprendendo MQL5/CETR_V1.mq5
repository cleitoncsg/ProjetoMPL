//+------------------------------------------------------------------+
//|                                                      CETR_V1.mq5 |
//|                           Copyright 2013, Cleiton da Silva Gomes |
//|                                    http://www.softwarecsg.com.br |
//+------------------------------------------------------------------+
#property copyright "Copyright 2013, Cleiton da Silva Gomes"
#property link      "http://www.softwarecsg.com.br"
#property version   "1.00"

#include  <Correlacao.mqh>

//+------------------------------------------------------------------+
//|  Método principal -> main                                        |
//+------------------------------------------------------------------+
#define  ALAVANCAGEM 1
int ticket;
int stop_loss = 1;
int take_profit = 2;
string nome = "s";
void OnTick(){

    //Correlacao c;
    calculo();
    
    printf("%f\n",calculo());
    
    
  }
  
  
void OnTrade(){}

void OnTradeTransaction(const MqlTradeTransaction& trans,
                        const MqlTradeRequest& request,
                        const MqlTradeResult& result){
   
   
 }

