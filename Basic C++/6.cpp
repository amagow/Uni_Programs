#include <iostream>
using namespace std;
int prime(int temp);
int main() {
  int input,count,i,r;
  count=0;
  cin>>input;
  for(i=input+1;count<10;i++){
    r= prime(i);
     if(r!=0){
      cout<<i<<endl;
      count++;
     }
  }
}
int prime(int temp){
  int i;
  for(i=2;i<temp;i++){
    if(temp%i==0)
      return 0;
  }
  return temp;
}
