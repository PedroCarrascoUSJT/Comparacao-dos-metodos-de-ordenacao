import java.util.*;


public class App {

    public static void main(String[] args) throws Exception {
        int qtdValor = 10000;
        long inicio;
        int[] vet = new int[qtdValor];
        long[] tempos = new long[5]; 
        
        // Bubble Sort
        
        preencheAleatorio(vet, 1000);
        
        System.out.println("Iniciando bubble sort com "+qtdValor+" valores.");
        inicio = System.nanoTime();
        
        bolha(vet);
        tempos[0] = System.nanoTime() - inicio;

        System.out.println("Bubble sort realizado em "+tempos[0]+"ns");

        // Selection Sort

        preencheAleatorio(vet, 1000);
        
        System.out.println("Iniciando selection sort com "+qtdValor+" valores.");
        inicio = System.currentTimeMillis();
        
        selecao(vet);
        tempos[1] = System.nanoTime() - inicio;

        System.out.println("Selection sort realizado em "+tempos[1]+"ns");

        // Insertion Sort

        preencheAleatorio(vet, 1000);
        
        System.out.println("Iniciando insertion sort com "+qtdValor+" valores.");
        inicio = System.currentTimeMillis();
        
        insercao(vet);
        tempos[2] = System.nanoTime() - inicio;

        System.out.println("Insertion sort realizado em "+tempos[2]+"ns");

        // Merge Sort

        preencheAleatorio(vet, 1000);
        
        System.out.println("Iniciando merge sort com "+qtdValor+" valores.");
        inicio = System.currentTimeMillis();
        
        mergeSort(vet);
        tempos[3] = System.nanoTime() - inicio;

        System.out.println("Merge sort realizado em "+tempos[3]+"ns");

        // Quick Sort

        preencheAleatorio(vet, 1000);
        
        System.out.println("Iniciando quick sort com "+qtdValor+" valores.");
        inicio = System.currentTimeMillis();
        
        quickSort(vet);
        tempos[4] = System.nanoTime() - inicio;

        System.out.println("Quick sort realizado em "+tempos[4]+"ns");

        //Análise
        long media = 0;

        for(int i = 0; i<tempos.length;i++){
            media += tempos[i];
        }
        media /= tempos.length;

        System.out.println("\nMédia de tempo entre os métodos de ordenação é de "+media+" ns.");

        System.out.println("\nComparações entre os métodos.\n");
        for(int i = 0;i<tempos.length;i++)
            for(int j = i+1; j<tempos.length;j++){
                boolean verificaMaior = tempos[i]> tempos[j];
                long comp = verificaMaior ? 
                    tempos[i] - tempos[j] : tempos[j] - tempos[i];
                
                String[] metodos = {"Bubble Sort","Selection sort", "Insertion Sort", "Merge Sort","Quick sort"};

                String outputVerificaMaior = verificaMaior ? metodos[i] : metodos[j];

                String output = "O "+ outputVerificaMaior + " é "+ comp + "ns mais lento que " + (verificaMaior ? metodos[j] : metodos[i]);

                System.out.println(output+"\n");
            }
        
    }
    //#region Bubble Sort
    public static void bolha(int vet[]){
        for(int i=1; i<= vet.length-1; i++)
            for(int j=0; j<vet.length-1; j++)
                if(vet[j]>vet[j+1])
                {
                    int aux = vet[j];
                    vet[j] = vet[j+1];
                    vet[j+1] = aux;
                }
      }
      //#endregion

      //#region Selection Sort
      public static void selecao(int vet[]){
            for(int i=0; i<vet.length-1; i++)
            {
                int indMenor = i;
                for(int j=i+1; j<vet.length; j++)
                {
                    if(vet[j] < vet[indMenor])
                        indMenor = j;
                }
                int aux = vet[i];
                vet[i] = vet[indMenor];
                vet[indMenor] = aux;
            }
      }
        //#endregion

      //#region Insertion Sort
      public static void insercao(int vet[]){
         for(int i=1; i<vet.length; i++)
         {
            int aux = vet[i];
            int j = i-1;        
            while(j>=0 && vet[j]>aux)
            {
               vet[j+1] = vet[j];
               j--;
            }
            vet[j+1] = aux;
         }
      }

      //#endregion

      //#region Merge Sort

      public static void mergeSort(int vet[])
      {
         int vetaux[] = new int[vet.length];
         mergeSort(vet, 0, vet.length-1, vetaux);  
      }
      
      public  static void mergeSort(int vet[], int inicio, int fim, int vetaux[])
      {
         if(fim-inicio >= 1)
         {
            int meio = (inicio + fim)/2;
            mergeSort(vet, inicio, meio, vetaux);
            mergeSort(vet, meio+1, fim, vetaux);
            mescla(vet, inicio, meio, fim, vetaux);
         }

       }
    
      public  static void mescla(int vet[], int inicio, int meio, int fim, int vetaux[])
      {
         int i=inicio;
         int j=meio+1;
         int k=inicio;
    
         while(k <= fim)
         {
            if(i <= meio && j <= fim)
            {
               if(vet[i] < vet[j])
               {
                  vetaux[k] = vet[i];
                  i++; k++;
               }
               else
               {
                  vetaux[k] = vet[j];
                  j++; k++;
               }
            }
            else if(i > meio)
            {
               vetaux[k] = vet[j];
               j++; k++;
            }
            else
            {
               vetaux[k] = vet[i];
               i++; k++;
            }
         }
       
         for(i=inicio; i <= fim; i++)
            vet[i] = vetaux[i];
      }

      //#endregion

      //#region Quick Sort
      public static void quickSort(int vet[]) {
        quickSort(vet, 0, vet.length-1); 
       } 
      
       private static void quickSort(int vet[], int esquerda, int direita) { 
        int indice = particao(vet, esquerda, direita); 
        if (esquerda < indice - 1) 
         quickSort(vet, esquerda, indice - 1); 
        if (indice < direita - 1) 
         quickSort(vet, indice + 1, direita); 
       }    
      
       private static int particao(int vet[], int esquerda, int direita) {
        int i = esquerda, j = direita, aux; 
        int pivo = vet[esquerda];     
        while (i < j) { 
         while (vet[j] > pivo){
          j--;
         }
         if(i >= j){
          return i;
         }
         aux = vet[i]; 
         vet[i] = vet[j]; 
         vet[j] = aux;
         i++;
         while (vet[i] < pivo){
          i++;
         }
         aux = vet[i]; 
         vet[i] = vet[j]; 
         vet[j] = aux;
         j--;
        } 
        return i; 
       }
      //#endregion

    public static void preencheAleatorio (int [] v, int max){
		Random r = new Random ();
		for (int i = 0; i < v.length; i++)
			v [i] = r.nextInt(max + 1);
	}

}
