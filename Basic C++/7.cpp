#include <iostream>
using namespace std;
int display(char arr1[3][3]){
	int i,j;
	for(i=0;i<3;i++){
		for(j=0;j<3;j++){
			cout<<arr1[i][j];
		}
		cout<<endl;
	}
}
int main() {
	int p1,p2,p3,p4;
	char arr[3][3]={{'1','2','3'},{'4','5','6'},{'7','8','9'}};
	display(arr);


  cout<<"x: ";
	cin>>p1;
	if(p1<3)
	  arr[0][p1-1]='x';
	else if (p1>3&&p1<6)
	  arr[1][p1-4]='x';
	else
	  arr[2][p1-7]='x';
	display(arr);


	do{
	  cout<<"o: ";
	  cin>>p2;
		if(p1==p2)
		  display(arr);
  }while(p1==p2);
	if(p2<3)
	  arr[0][p2-1]='o';
	else if (p2>3&&p2<6)
	  arr[1][p2-4]='o';
	else
    arr[2][p2-7]='o';
	display(arr);


	do{
	  cout<<"x: ";
	  cin>>p3;
		if(p3==p2||p3==p1)
		  display(arr);
  }while(p3==p2||p3==p1);
	if(p3<3)
	  arr[0][p3-1]='x';
	else if (p3>3&&p3<6)
	  arr[1][p3-4]='x';
	else
    arr[2][p3-7]='x';
	display(arr);


	do{
	  cout<<"o: ";
	  cin>>p4;
		if(p4==p1||p4==p2||p4==p3)
		  display(arr);
  }while(p4==p1||p4==p2||p4==p3);
	if(p4<3)
	  arr[0][p4-1]='o';
	else if (p4>3&&p4<6)
	  arr[1][p4-4]='o';
	else
    arr[2][p4-7]='o';
	display(arr);
}
