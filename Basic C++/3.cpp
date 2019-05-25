#include <iostream>
using namespace std;
int factorial(int temp);
int main() {
	int num1,num2,num3,sum;
	cin>>num1>>num2>>num3;
	sum = factorial(num1) + factorial(num2)+ factorial(num3);
  cout<<sum<<endl;
}
int factorial(int temp){
	int i,fact;
	fact=1;
  if(temp==0)
	fact=1;
	else{
	  for(i=1;i<=temp;i++){
		fact*=i;
	  }
  }
	return fact;
}
