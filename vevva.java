for (int i = 1; i <= n; i++) {
    for (int j = 1; j*a < i; j++) {
        if((i - j*a) % b == 0){
            System.out.print("" + i + " = " + a);
            for (int k = 1; k < j; k++) {
                System.out.print(" + " + a);
            }
            for (int k = 0; k < (i-j*a)/b; k++) {
                System.out.print(" + " + b);
            }
            System.out.println();
        }
    }
}
