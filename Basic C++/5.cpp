#include <iostream>
using namespace std;
int main() {
  int m,n,i,j;
  cin>>m>>n;
  for(i=0,j=0;i<m||j<n;i++,j++){
    if(i<m)
      cout<<"y";
    if(j<n)
    cout<<"z";
  }
  cout<<endl;
}
