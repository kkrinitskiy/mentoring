package data_bases.hw2.task_03.transportCompanyCore;

import data_bases.hw2.task_03.daos.ShippedGoodsDao;
import data_bases.hw2.task_03.entities.ShippedGoods;
import data_bases.hw2.task_03.entities.Transportable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Container {
    private final static ShippedGoodsDao goodsDao = new ShippedGoodsDao();
    private final Integer width;
    private final Integer height;
    private final Integer length;
    @Getter
    @Setter
    private Transportable item;

    public Container(Integer width, Integer height, Integer length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public Integer getVolume() {
        return width * height * length;
    }

    public boolean loadingItem(Transportable item) {
        if(canTransport(item)){
            this.item = item;
            goodsDao.add(ShippedGoods.builder().name(item.toString()).build());
            return true;
        }else {
            return false;
        }
    }

    public boolean canTransport(Transportable item) {
        return this.width >= item.getWidth() && this.height >= item.getHeight() && this.length >= item.getLength();
    }
}

