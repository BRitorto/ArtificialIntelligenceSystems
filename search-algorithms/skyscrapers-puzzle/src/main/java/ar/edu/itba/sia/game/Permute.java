package ar.edu.itba.sia.game;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Permute {

    private HashSet<Integer[]> permutations;

    public Permute(Integer n){
        permutations = new HashSet<>();
        Integer[] a = new Integer[n];
        for(int i = 1; i <= n ; i++)
            a[i-1] = i;

        permute(java.util.Arrays.asList(a), 0);
    }

    public HashSet<Integer[]> getPermutations() {
        return permutations;
    }

    public void permute(java.util.List<Integer> arr, int k){

        for(int i = k; i < arr.size(); i++){
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            //System.out.println(java.util.Arrays.toString(arr.toArray()));
            permutations.add(Arrays.copyOf(arr.toArray(), arr.toArray().length, Integer[].class));
        }

    }
//    public static void main(String[] args){
//        Permute.permute(java.util.Arrays.asList(1,2,3,4), 0);
//    }
}