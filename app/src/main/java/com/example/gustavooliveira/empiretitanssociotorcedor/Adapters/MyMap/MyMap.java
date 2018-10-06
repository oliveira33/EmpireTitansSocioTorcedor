package com.example.gustavooliveira.empiretitanssociotorcedor.Adapters.MyMap;

import com.example.gustavooliveira.empiretitanssociotorcedor.R;

import java.util.HashMap;

public class MyMap {

    public HashMap<String, Integer> gerarMap() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Atletic Celtic", R.drawable.clube_aletic_celtic);
        map.put("SE Parani", R.drawable.clube_parani);
        map.put("Real Salt Lake", R.drawable.clube_real_salt_lake);
        map.put("Toronto FC", R.drawable.clube_toronto);
        map.put("FC Dallas", R.drawable.clube_dallas);
        map.put("Dundee FC", R.drawable.clube_dundee);
        map.put("SE Hamilton", R.drawable.clube_hamilton);
        map.put("Rangers", R.drawable.clube_rangers);
        map.put("Livingston", R.drawable.clube_livingston);

        return map;
    }

}
