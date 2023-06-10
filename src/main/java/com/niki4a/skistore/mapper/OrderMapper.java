package com.niki4a.skistore.mapper;

import com.niki4a.skistore.controller.resources.OrderResource;
import com.niki4a.skistore.entity.CustomerOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ProductMapper.class})
public interface OrderMapper {
   // OrderMapper ORDER_MAPPER = org.mapstruct.factory.Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "orderResource.user", target = "user.username")
    CustomerOrder toCustomerOrder(OrderResource orderResource);

    @Mapping(source = "customerOrder.user.username", target = "user")
    OrderResource fromCustomerOrder(CustomerOrder customerOrder);

    List<CustomerOrder> toCustomerOrderList(List<OrderResource> orderResourceList);

    List<OrderResource> fromCustomerOrderList(List<CustomerOrder> customerOrderList);
}
