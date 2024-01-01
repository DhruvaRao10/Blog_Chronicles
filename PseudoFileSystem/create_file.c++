#include <bits/stdc++.h>
using namespace std;

struct BlockRange{
      int start ; 
      int end ; 
};

struct FileRecord{
    //   int createDate ; 
      int createTime ;                   
      vector<BlockRange>blockRanges ;           
} fat;

unordered_map<string,FileRecord>dirmp ; 


void increaseFileSize(string &filePath , streamoff sizeBytes){
    ofstream OFile(filePath , ios::app | ios::binary) ; 
    if(!OFile){
          cout << "Error opening the file" << filePath << endl ; 
    }
    char zero = '\0' ; 
    for(streamoff i=0 ; i<sizeBytes ; i++){
           OFile.write(&zero, sizeof(zero)) ; 
    }                                         
    OFile.close() ; 
}     


void insertRecord(string &filePath){
          FileRecord record1{time(nullptr),{{1,3},{8,10}}}  ;                                          
          FileRecord record2{time(nullptr),{{2,6},{8,10}}} ;
          FileRecord record3{time(nullptr),{{12,16},{16,18}}} ; 


          dirmp.insert({"file1",record1}) ;
          dirmp.insert({"file2",record2}) ; 
          dirmp.insert({"file3",record3}) ; 

 }


 void deleteRecord(string &filePath , streamoff sizeBytes , string fileSearch , unordered_map<string,FileRecord>dirmp){
             if(dirmp.find(fileSearch) != dirmp.end()){
                      dirmp.erase(fileSearch) ; 
            }else{
                 cout << "File Not Found" << endl ; 
            }                             
 }


int main() {
  // Create and open a text file           
//   unordered_map<string,FileRecord>dirmp ; 
  
  string filePath = "C:\\test\\dfsfileholder" ;
  
  int buffersize = 64 ;
  char buffer[buffersize] ; 
  memset(buffer , 0 , buffersize) ; 
    
  ofstream File(filePath,ios::binary);           
  insertRecord(filePath) ; 
                    
  uint32_t value = 3 ;
  // Write to the file                              
    // if (File.is_open()) {                                                             
    //     // Write to the file
    //     // File << "File is empty and here is dappi!" ;

    //     cout << "File open successfully." << endl ;
    // }                                                                            
    // else {
    //     cerr << "Error creating or opening the file." << endl ;
    //     return 1 ;                                            
    // }
   
   // increaseFileSize(filePath , 1024*1024*1024); 

    cout << "Writing to Disk" << endl ; 
    // int offset = 0 ; 
    if(File.is_open()){
        cout <<"wrote map contents to file"<< endl;
        File.write(reinterpret_cast<char*>(&value),sizeof(uint32_t)) ;

        while(value--){
           for(auto it:dirmp){
                //   cout << " one"<<endl;
                 string fileName = it.first ; 
                //   cout <<" two  "<<   endl;
                 uint32_t sz = min(fileName.size(),static_cast<uint32_t>(buffersize-1)) ;
                //   cout <<" three  "<<   endl ;
                 strncpy(buffer , fileName.c_str() , sz) ;
                //   cout <<" four  "<<   endl ;
                 //   break ;   
           
                   cout <<"wrote filename to file :  "<< buffer << " " << buffersize <<  endl ;
                   File.write(buffer , buffersize) ; 
                   int numBlockRanges = fat.blockRanges.size() ;
                //    if(numBlockRanges == 0)
                //        numBlockRanges = 1 ;
            
                  // cout <<" six  "<<   endl ;

                  File.write(reinterpret_cast<const char*>(&numBlockRanges) , sizeof(uint32_t)) ; 
                  cout << "wrote  numblockrange" << numBlockRanges <<   endl ;

                 for(auto it:dirmp){
                     const FileRecord record = it.second ; 
                     File.write(reinterpret_cast<const char*>(&record.blockRanges[0].start),sizeof(uint32_t)) ; 
                     File.write(reinterpret_cast<const char*>(&record.blockRanges[0].end),sizeof(uint32_t)) ; 
            //  break ; 
                 }
             }

        }
        File.close()  ; 

        // cout <<" five  "<<   endl ;
        
       
        // for(auto it:dirmp){                                      
        //        File << it.second.createTime  ;          
        //        for(auto br:it.second.blockRanges){                                      
        //                 File << br.start << br.end ;
        //                 cout << " wrote one start " << br.start  << " and end " << br.end << endl ; 
        //        }                                                          
        // }
          
    }else{
         cerr << "Error opening the file to write the file records" << endl ; 
         return 1 ;                                                          
    }

    ifstream  inFile =  ifstream(filePath , ios::binary | ios::in) ;
    cout << " opened filepath for reading  " << filePath << endl ;
    //ifstream  inFile(filePath,ios::binary) ;
    
    uint32_t entries = 0 ;
    char filename[64] ; 

    // if(inFile.is_open()){
    //   const size_t n = 4 ; 
    //      cout << "in read part" << endl;  
    //      inFile.seekg(0, ios::beg) ;
    //      inFile.read(reinterpret_cast<char*>(&entries),sizeof(entries)) ;
    //      cout << " entries read from are  " << entries << endl ;


        
    //      for(int i=0 ; i<entries ; i++){                                
    //            inFile.read(reinterpret_cast<char*>(&filename) , 64)  ;                                                        
    //            cout << "The file names read from the file are" << " " << filename << endl ; 
    //            uint32_t numBlockRanges = 0;
    //            inFile.read(reinterpret_cast<char*>(&numBlockRanges), sizeof(uint32_t));
    //            cout << "Numblock range read from file: " << numBlockRanges << endl;

    //           for (uint32_t j = 0; j < numBlockRanges; ++j) {
    //               uint32_t startRange, endRange ;                                       
    //               inFile.read(reinterpret_cast<char*>(&startRange), sizeof(uint32_t)) ;
    //               inFile.read(reinterpret_cast<char*>(&endRange), sizeof(uint32_t)) ;                  
    //               cout << "Block range read from file: " << startRange << " - " << endRange << endl ;
    //          }
    //       }

    //   }

    //    else{
    //       cerr << "Error opening the file records " << endl ; 
    //       return 1  ; 
    //    }
      
                                    
      if(inFile.is_open()){
          FileRecord record  ;                   
          uint32_t startRange=10,endRange=10 , numBlockRanges ;          
                   const size_t n = 4 ; 
         cout << "in read part" << endl;  
         inFile.seekg(0, ios::beg) ;
         inFile.read(reinterpret_cast<char*>(&entries),sizeof(entries)) ;
         cout << " entries read from are  " << entries << endl ;


          for(int i=0 ; i<entries ; i++){              
             inFile.read(reinterpret_cast<char*>(&numBlockRanges),sizeof(uint32_t)) ; 
             cout << "read the block ranges = " << numBlockRanges << endl ; 
             cout << numBlockRanges ;                  
             //inFile.read(reinterpret_cast<char*>(&record.blockRanges[0]),sizeof(uint32_t)) ;
            for(int j=0 ; j<numBlockRanges ; j++){
               inFile.read(reinterpret_cast<char*>(&startRange),sizeof(uint32_t)) ;
               cout << " read the start block range  = " << startRange << endl ;
              //inFile.read(reinterpret_cast<char*>(&record.blockRanges[1]),sizeof(uint32_t)) ;
              inFile.read(reinterpret_cast<char*>(&endRange),sizeof(uint32_t)) ;
               cout << " read the end  block range  = " << endRange << endl ;
            }
        }
      }
      
    //   inFile.close() ; 

      
    //   inFile >> entries;
     if (inFile.fail()){
        // Get the error code
        std::ios_base::iostate state = inFile.rdstate();

  
        // Check for specific error bits
        if (state & std::ios_base::eofbit){
            std::cout << "End of file reached." << std::endl;
        }
        if (state & std::ios_base::failbit){
            std::cout << "Non-fatal I/O error occurred." << std::endl;
        }
        if (state & std::ios_base::badbit){
            std::cout << "Fatal I/O error occurred." << std::endl;
        }                                                             

        // Print system error message
        std::perror("Error: ");            

        cout << "The number of entries is :" << " " << entries << endl ;
    }
    else{
        cout << " no problems reading file " << endl ;

    }
    
        inFile.close() ;


    
}
