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
//|  M�todo principal -> main                                        |
//+------------------------------------------------------------------+
void OnTick(){

    //Correlacao c;
    calculo();
    
    printf("%f\n",calculo());
    
    
  }

