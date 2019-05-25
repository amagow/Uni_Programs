#include <iostream>

const int SIZE = 8;
//----------------------DO NOT CHANGE ANYTHING ABOVE THIS LINE------------------
void read_array(int arrt[]){
  for(int i=0;i<SIZE;i++){
    std::cin>>arrt[i];
  }

}
int read_number_of_shifts(){
  int n;
  std::cin>>n;
  return n;
}
void shift_array(int arrt[],int n){
  int arrt2[SIZE];
  for(int i=0;i<SIZE;i++){
    arrt2[i]=arrt[i];
  }
  if(n>0){
    for(int i=0;i<SIZE;i++){
      arrt[(i+n)%8]=arrt2[i];
     }
   }
  else{
    for(int i=0;i<SIZE;i++){
      arrt[(SIZE+i+n%8)%8]=arrt2[i];
     }
   }
  }


//----------------------DO NOT CHANGE ANYTHING BELOW THIS LINE------------------
int main()
{
  int arr[SIZE]{};

  read_array(arr);
  int shifts = read_number_of_shifts();
  shift_array(arr, shifts);

  for (int i = 0; i < SIZE; ++i)
  {
    std::cout << arr[i] << ' ';
  }
  std::cout << std::endl;

  return 0;
}
