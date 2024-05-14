    package lesson_05.task_41.models;

    import java.util.ArrayList;
    import java.util.List;

    import lesson_05.task_41.Item;

    public class Container<T extends Item> extends Item {
        private T item;

        public Container(int width, int length, int height) {
            super(width, length, height);
        }

        public Container(){
            this(0,0,0);
        }
        
        public boolean loadItem(T itemToLoad){
            if(getVolume() > itemToLoad.getVolume()){
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

        public List<Container<T>> factory(int containersCount, int width, int length, int height){
            List<Container<T>> result = new ArrayList<>();
            for (int i = 0; i < containersCount; i++) {
                result.add(new Container<>(width, length, height));
            }
            return result;
        }
    }
