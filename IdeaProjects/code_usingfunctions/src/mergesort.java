public class mergesort {
    void merge(int arr[],int l,int m,int r)
    {
        int n1=m-l+1;
        int n2=r-m;

        int L[]=new int [n1];
        int R[]=new int [n2];

        for(int i=0;i<n1;i++)
        {
            L[i]=arr[l+i];
            for(int j=0;j<n2;j++)
            {
                R[j]=arr[m+1+j];
            }
        }


    }
}
