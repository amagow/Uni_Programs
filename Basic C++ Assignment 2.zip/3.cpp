#include <iostream>

//----------------------DO NOT CHANGE ANYTHING ABOVE THIS LINE------------------
void read_lists(int arrt1[], int arrt2[], int arrt3[], int n){
    for(int j =0;j<n;j++){
    std::cin>>arrt1[j];
    }
    for(int j =0;j<n;j++){
    std::cin>>arrt2[j];
    }
    for(int j =0;j<n;j++){
    std::cin>>arrt3[j];
    }
}
void display_common_integers(int arrt1[],int arrt2[],int arrt3[],int n){
  int i,j,k,counter = 0;
   for (i=0;i<n;i++)
   {
     for (j=0;j<n;j++)
     {
       if(arrt1[i]==arrt2[j])
       {
         for(k=0;k<n;k++){
           if(arrt2[j]==arrt3[k])
           {
             std::cout<<arrt3[k]<<std::endl;
             counter++;
             arrt1[i]='\0';
             arrt2[j]='\0';
             arrt3[k]='\0';
             break;
           }
         }
         break;
       }
       continue;
     }
   }
   if(counter==0)
   std::cout<<"None"<<std::endl;
}


//----------------------DO NOT CHANGE ANYTHING BELOW THIS LINE------------------

int main()
{
  const int SIZE = 8;
  int arr1[SIZE]{};
  int arr2[SIZE]{};
  int arr3[SIZE]{};

  read_lists(arr1, arr2, arr3, SIZE);
  display_common_integers(arr1, arr2, arr3, SIZE);

  return 0;
}
