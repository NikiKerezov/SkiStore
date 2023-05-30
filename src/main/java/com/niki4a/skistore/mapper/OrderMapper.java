package com.niki4a.skistore.mapper;

import com.niki4a.skistore.controller.resources.OrderResource;
import com.niki4a.skistore.entity.CustomerOrder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {UserMapper.class, ProductMapper.class})
public interface OrderMapper {
    OrderMapper ORDER_MAPPER = org.mapstruct.factory.Mappers.getMapper(OrderMapper.class);

    OrderResource toOrderResource(CustomerOrder order);
    CustomerOrder fromOrderResource(OrderResource orderResource);

    List<OrderResource> toOrderResourceList(List<CustomerOrder> orderList);
    List<CustomerOrder> fromOrderResourceList(List<OrderResource> orderResourceList);
}
