#include <iostream>

//----------------------DO NOT CHANGE ANYTHING ABOVE THIS LINE------------------
void read_word(char arr[],int& length){
  std::cin>>length;
  for(int i=0;i<length;i++)
    std::cin>>arr[i];
}
char read_target(){
  char c;
  std::cin>>c;
  return c;
}
void delete_target(char arr[],int &length,char c){
  int i,counter=0;
    for( i=0;i<length;i++)
    {
      if(arr[i]==c)
      {
        counter++;
        for(int j=i;j<length;j++)
        arr[j]=arr[j+1];
        i--;

      }
    }
  length-=counter;
}

//----------------------DO NOT CHANGE ANYTHING BELOW THIS LINE------------------

int main()
{
  const int MAXLENGTH = 10;

  char word[MAXLENGTH]{};
  int word_length = 0;
  read_word(word, word_length);

  char target = read_target();

  delete_target(word, word_length, target);

  for (int i = 0; i < word_length; ++i)
  {
    std::cout << word[i];
  }
  std::cout << std::endl;

  return 0;
}
