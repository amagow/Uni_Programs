#include <iostream>
#include <string>

using namespace std;

struct Point {
    double x;
    double y;
};

struct Circle {
    string tag;
    Point center;
    double radius;
};

//----------------------DO NOT CHANGE ANYTHING ABOVE THIS LINE------------------


int main()
{
  int N;
  cin>>N;
  Circle*inputC=new Circle[N];
  for(int i=0;i<N;i++)
  {
    cin>>inputC[i].tag;
    cin>>inputC[i].center.x;
    cin>>inputC[i].center.y;
    cin>>inputC[i].radius;
  }
  double dx,dy,rp,rn;
  for(int i=0;i<N;i++)
  {
    cout<<inputC[i].tag<<":";
    for(int j=0;j<N;j++)
    {
      if(i!=j)
      {
        dx=inputC[i].center.x-inputC[j].center.x;
        dy=inputC[i].center.y-inputC[j].center.y;
        rp=inputC[i].radius+inputC[j].radius;
        rn=inputC[i].radius-inputC[j].radius;
        if(((dx*dx+dy*dy)>rp*rp)||((dx*dx+dy*dy)<rn*rn));
        else
        cout<<" "<<inputC[j].tag;
      }
    }
    cout<<endl;
  }

}
