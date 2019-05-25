#include <iostream>
#include <string>
using namespace std;

//----------------------DO NOT CHANGE ANYTHING ABOVE THIS LINE------------------
int main()
{
  string s,t,subs;
  //Inputs line from user
  getline(cin,s);
  //Inputs the word to check for anagram from user
  cin>>t;
  int slength=s.length();
  int tlength=t.length();
  string temps = s;
  int c1=0,c2=0,c3=0,sublength;
  for(int i=0;i<slength;i++)
  {
    if(temps[i]!=' ')
    {
      for(int j=i;j<i+tlength+c1;j++)
      {
        if(temps[j]==' ')
        {
          c1++;
        }
        sublength=c1+tlength;
      }
      subs=temps.substr(i,sublength);
      for(int j=0;j<sublength;j++)
      {
        for(int k=0;k<sublength;k++)
        {
          if(t[j]==subs[k])
          {
            if(subs[k]=='\0'||t[j]=='\0')
            break;
            c2++;
            subs[k]='*';
            break;
          }
        }
      }

    if(c2+c1==sublength)
      {
        cout<<i<<' '<<i+sublength-1<<endl;
      }
    c2=0;
    c1=0;
    sublength=0;
    }
  }
}
