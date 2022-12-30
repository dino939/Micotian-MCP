package com.denger.micotian.utils;


public class GlichesText {
    long mc;
    int chan = 1000;
    String text;
    boolean doGlich = true;
    int glichState = 0;

    public GlichesText(String text){
        this.text = text;
        mc = System.currentTimeMillis();
    }

    public GlichesText(String text, int chane){
        this.text = text;
        mc = System.currentTimeMillis();
        chan = chane;
    }

    public String getText() {
        String rettext = "";
        try {
            int count = (int) ((System.currentTimeMillis() - mc) / 100);
            if (count > 0){
                mc = System.currentTimeMillis();
            }


            if (doGlich){

                String symbol = "";
                int chanhe = MathUtils.randomize(0, 3);
                switch (chanhe){
                    case 0:
                        symbol = "!";
                        break;
                    case 1:
                        symbol = "&";
                        break;
                    case 2:
                        symbol = "%";
                        break;
                    case 3:
                        symbol = "^";
                        break;
                }
                if (glichState == 0){
                    rettext+=symbol;
                    rettext+=text.substring(1, text.length());
                }else if (glichState == text.length()){

                    rettext+=text.substring(0, text.length() - 1);
                    rettext+=symbol;
                }else {
                    rettext+=text.substring(0, glichState-1);
                    rettext+=symbol;
                    rettext+=text.substring(glichState+1, text.length());
                }

                if (count < 2){
                    glichState+=(1 * count);
                }
                if (glichState == text.length()+1){
                    doGlich = false;
                }
            }else {
                if (MathUtils.randomize(0, chan) == 1){
                    doGlich = true;
                    glichState = 0;
                }
                rettext = text;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        return rettext;
    }
}
