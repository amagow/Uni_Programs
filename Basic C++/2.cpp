#include <iostream>
using namespace std;
int main() {
	int input;
	cin>>input;
	if(input>=0&&input<=17)
	cout<<"junior"<<endl;
	if(input>=18&&input<=64)
	cout<<"adult"<<endl;
	if(input>=65&&input<=99)
	cout<<"senior"<<endl;
}
