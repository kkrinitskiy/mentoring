    package lesson_05.task_41.models;

    import java.util.*;

    import lesson_05.task_41.Item;
    import lesson_05.task_41.exceptions.DimensionLessThenOneException;

    public class Container<T extends Item> extends Item {
        private T item;

        public Container(int width, int length, int height) throws DimensionLessThenOneException {
            super(width, length, height);
        }

        public Container() throws DimensionLessThenOneException {
            this(1,1,1);
        }
        
        public boolean loadItem(T itemToLoad){
            if(canFit(itemToLoad)){
                this.item = itemToLoad;
                return true;
            }
            return false;
        }

        public T unloadItem(){
            T result = item;
            item = null;
            return result;
        }

        public List<Container<T>> factory(int containersCount, int width, int length, int height) throws DimensionLessThenOneException{
            List<Container<T>> result = new ArrayList<>();
            for (int i = 0; i < containersCount; i++) {
                result.add(new Container<>(width, length, height));
            }
            return result;
        }

        public int getItemVolume(){
            return this.item.getVolume();
        }

        public boolean canFit(T item) {
            int[] containerDimensions = {this.getHeight(), this.getLength(), this.getWidth()};
            int[] itemDimensions = {item.getHeight(), item.getLength(), item.getWidth()};
    
            Arrays.sort(containerDimensions);
            Arrays.sort(itemDimensions);
    
            return itemDimensions[2] <= containerDimensions[2] &&
                   itemDimensions[1] <= containerDimensions[1] &&
                   itemDimensions[0] <= containerDimensions[0];
        }
    }
