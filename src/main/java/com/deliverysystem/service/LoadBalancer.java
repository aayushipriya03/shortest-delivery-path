package com.deliverysystem.service;

import org.springframework.stereotype.Service;

@Service
public class LoadBalancer {
   static  int cnt=0;



   private Server server1 = new Server(1,"server1");
   private Server server2 = new Server(2,"server2");
   private Server server3 = new Server(1,"server3");


   public void loadBalancing(){
      cnt++;
      int total = 3;

      int currentCnt = cnt%total;
      if(currentCnt == 1){
         server1.serverCall();
      }
      else if(currentCnt == 2){
         server2.serverCall();
      }else {
         server3.serverCall();
      }

   }



}
