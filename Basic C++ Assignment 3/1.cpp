#include <iostream>
#include <string>
using namespace std;

//----------------------DO NOT CHANGE ANYTHING ABOVE THIS LINE------------------
string inputS()
{
  string s;
  cin>>s;
  return s;
}
int main()
{
  string s1,s2,s3;
  s1 = inputS();
  s2 = inputS();
  int max,carrydigit=0;
  if(s1.length()>s2.length())
    {
      max = s1.length();
      int a=s1.length()-s2.length();
      for(long i=0;i<a;i++)
      {
        s2='0'+s2;
      }
    }
  else if(s1.length()<s2.length())
  {
    max = s2.length();
    int a=s2.length()-s1.length();
    for(long i=0;i<a;i++)
    {
      s2='0'+s2;
    }
  }
  else
    max = s1.length();
  for(int i=max-1;i>=0;i--)
  {
    if(carrydigit==1)
    {
      if(s1[i]+s2[i]==96)
      {
        s3='1'+s3;
        carrydigit=0;
      }
      else if(s1[i]+s2[i]==97)
        {
          s3='0'+s3;
          carrydigit=1;
        }
      else if(s1[i]+s2[i]==98)
        {
          s3='1'+s3;
          carrydigit=1;
        }
    }
    else
    {
      if(s1[i]+s2[i]==96)
      {
        s3='0'+s3;
        carrydigit=0;
      }
      else if(s1[i]+s2[i]==97)
      {
        s3='1'+s3;
        carrydigit=0;
      }
      else if(s1[i]+s2[i]==98)
      {
        s3='0'+s3;
        carrydigit=1;
      }
    }
  }
  if(carrydigit==1)
  {
    s3='1'+s3;
  }
  cout<<s3<<endl;

}
