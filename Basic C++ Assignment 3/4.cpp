#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib>

using namespace std;

//----------------------DO NOT CHANGE ANYTHING ABOVE THIS LINE------------------



int main()
{
  int N;
  string filename,swi,swi2,salmost;
  long c=0,c2=0,c3=0;
  cin>>filename>>N;
  ifstream fin;
  ofstream fout;
  fin.open(filename.c_str());
  if(fin.fail())
    exit(1);
  while(fin>>swi)
  {
    swi2+=swi;
    c++;
  }
  string substr,substr2;
  long lswi2=swi2.length();
  long pos=0;
  int lsubstr,lsubstr2,lcount=0;
  for(long i=0;i<lswi2;i++)
  {
    if(swi2[i]==' ')
    {
        substr=swi2.substr(pos,i-pos);
        pos=i;
        lsubstr=substr.length();
        if(lsubstr>=N)
        {
          if(pos!=0)
          {
            substr2=substr.substr(1,lsubstr-1);
            lsubstr2=substr2.length();
            salmost+=substr2+'\n';
          }
          else
            salmost+=substr+'\n';
        }
        else if(lsubstr<N)
        {
          if(pos!=0)
          {

          }
          else
          {

          }
        }
    }
  }
  int a,b;
  a=filename.find('/');
  b=filename.find('.');
  string ofsubstr=filename.substr(a,b-a);
  string ofname="yourOutput"+ofsubstr+"_formatted.txt";
  fout.open(ofname.c_str());
  if(fout.fail())
    exit(1);
  int divisor,rem;
  divisor=N/10;
  rem=N%10;
  for(int i=0;i<divisor;i++)
  {
    fout<<"0123456789";
  }
  for(int i=0;i<rem;i++)
  {
    fout<<i;
  }
  fout<<endl;
  if(swi2.empty())
  {
    fout<<swi2;
    cout<<"0 formatted lines written to "+ofname<<endl;
  }
  else
  {
    fout<<swi2<<endl;
    cout<<"1 formatted lines written to "+ofname<<endl;
  }



}
