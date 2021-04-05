package client.entityClass;

import java.util.Objects;

public class Goods {

    private int id_goods;
    private String brand;
    private String model;
    private int year;
    private String os;
    private String type;
    private String vendercode;
    private double cost;

    public Goods() {
    }

    public Goods(int id_goods, String brand, String model, int year, String os, String type, String vendercode, double cost) {
        this.id_goods = id_goods;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.os = os;
        this.type = type;
        this.vendercode = vendercode;
        this.cost = cost;
    }

    public int getId_goods() {
        return id_goods;
    }

    public void setId_goods(int id_goods) {
        this.id_goods = id_goods;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVendercode() {
        return vendercode;
    }

    public void setVendercode(String vendercode) {
        this.vendercode = vendercode;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return id_goods == goods.id_goods &&
                year == goods.year &&
                Double.compare(goods.cost, cost) == 0 &&
                Objects.equals(brand, goods.brand) &&
                Objects.equals(model, goods.model) &&
                Objects.equals(os, goods.os) &&
                Objects.equals(type, goods.type) &&
                Objects.equals(vendercode, goods.vendercode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_goods, brand, model, year, os, type, vendercode, cost);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id_goods=" + id_goods +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", os='" + os + '\'' +
                ", type='" + type + '\'' +
                ", vendercode='" + vendercode + '\'' +
                ", cost=" + cost +
                '}';
    }
}
