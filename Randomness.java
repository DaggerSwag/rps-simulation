public class Randomness {
    // private int arr[]=new int[2];
    public int[] getRandomness(){
        int x=0,y=0;
        int random=(int)Math.floor(Math.random()*8);
        int val=new MyConstants().MOVEMENT_SPEED;
        switch(random)
        {
            case 0:
                x=-val;
                y=val;
                break;
            case 1:
                x=0;
                y=val;
                break;
            case 2:
                x=val;
                y=val;
                break;
            case 3:
                x=val;
                y=0;
                break;
            case 4:
                x=val;
                y=-val;
                break;
            case 5:
                x=0;
                y=-val;
                break;
            case 6:
                x=-val;
                y=-val;
                break;
            case 7:
                x=-val;
                y=0;
                break;
        }
        // arr[0]=x;
        // arr[1]=y;
        return new int[]{x,y};
    }
}
