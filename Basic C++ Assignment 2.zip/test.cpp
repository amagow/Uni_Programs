#include <cstdio>
#include <iostream>
#include <memory>
#include <stdexcept>
#include <string>
#include <array>
using namespace std;
string exec(string cmd) {
    array<char, 128> buffer;
    string result;
    shared_ptr<FILE> pipe(popen(cmd.c_str(), "r"), pclose);
    if (!pipe) throw std::runtime_error("popen() failed!");
    while (!feof(pipe.get()))
        if (fgets(buffer.data(), 128, pipe.get()) != nullptr)
            result += buffer.data();
    return result;
}
void test(int taskNumber, int numberOfTestCases) {
  exec("g++ -pedantic-errors -std=c++11 "+to_string(taskNumber)+".cpp -o"+to_string(taskNumber));
  for (int i=1;i<=numberOfTestCases;i++) {
    string filename = to_string(taskNumber)+"_"+to_string(i)+".txt";
    cout << taskNumber << "_" << i << ": ";
    exec("more input/"+filename+" | ./"+to_string(taskNumber)+" > yourOutput/"+filename);
    string result = exec("diff yourOutput/"+filename +" expectedOutput/"+filename);
    if (result.size()==0) cout << "PASSED" << endl;
    else cout << "FAILED" << endl;
    cout << result;
  }
  cout << endl;
}
int main() {
  exec("rm -f 1 2 3 4 5 6");
  int t;
  cout << "Task number (1 to 6, or 0 to run all): ";
  cin >> t;
  if (t==1||t==0) test(1, 6);
  if (t==2||t==0) test(2, 4);
  if (t==3||t==0) test(3, 4);
  if (t==4||t==0) test(4, 3);
  if (t==5||t==0) test(5, 3);
  if (t==6||t==0) test(6, 4);
}
