#include <iostream>

const int MAXDIM = 10;

//----------------------DO NOT CHANGE ANYTHING ABOVE THIS LINE------------------
void read_matrix(char arr[][MAXDIM],int& n){
  std::cin>>n;
  for(int i=0;i<n;i++){
    for(int j=0;j<n;j++)
    std::cin>>arr[i][j];
  }
}
void display_with_fill(char arr[][MAXDIM],int n){
  int i,j,k;
  for(i =0;i<n;i++)
  {
    for(j=0;j<n;j++)
    {
      if(arr[i][j]=='x')
      {
         for(k=0;k<n;k++)
         {
           if(k!=i && arr[k][j]=='x')
            continue;
          else
            arr[k][j]='1';
          }
          for(k=0;k<n;k++)
          {
            if(k!=j && arr[i][k]=='x')
             continue;
            else
             arr[i][k]='1';
          }

    }

  }

   }
  for(i=0;i<n;i++)
  {
    for(j=0;j<n;j++)
    {
      if(arr[i][j]=='1')
      {
        arr[i][j]='x';
      }
    }
  }
  for(i=0;i<n;i++)
  {
    for(j=0;j<n;j++)
    {
      std::cout<<arr[i][j]<<" ";
    }
    std::cout<<std::endl;
  }
}

//----------------------DO NOT CHANGE ANYTHING BELOW THIS LINE------------------

int main()
{
  char matrix[MAXDIM][MAXDIM]{};
  int dim = 0;

  read_matrix(matrix, dim);
  display_with_fill(matrix, dim);

  return 0;
}
