package com.mqttsnet.thinglinks.link.api;

import com.mqttsnet.thinglinks.common.core.constant.ServiceNameConstants;
import com.mqttsnet.thinglinks.common.core.domain.R;
import com.mqttsnet.thinglinks.link.api.domain.product.entity.Product;
import com.mqttsnet.thinglinks.link.api.domain.product.entity.ProductProperties;
import com.mqttsnet.thinglinks.link.api.domain.product.entity.ProductServices;
import com.mqttsnet.thinglinks.link.api.factory.RemoteProductFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: thinglinks
 * @description: 产品管理服务
 * @packagename: com.mqttsnet.thinglinks.link.api
 * @author: shisen
 * @date: 2022-07-26
 **/
@FeignClient(contextId = "remoteProductService", value = ServiceNameConstants.THINGLINKS_LINK, fallbackFactory = RemoteProductFallbackFactory.class)
public interface RemoteProductService {

    /**
     * 通过标识查询产品
     *
     * @param productIdentification
     * @return
     */
    @GetMapping("/product/api/select-by-product-identification/{productIdentification}")
    public R<Product> selectByProductIdentification(@PathVariable("productIdentification") String productIdentification);

    /**
     * 查询服务信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/product-services/api/{id}")
    public R<ProductServices> selectProductServicesById(@PathVariable("id") Long id);


    /**
     * 查询属性信息
     *
     * @return
     */
    @GetMapping("/product-properties/api/{id}")
    public R<ProductProperties> selectByIdProperties(@PathVariable("id") Long id);

    @GetMapping("/product/selectAllProduct/{status}")
    public R<List<Product>> selectAllProduct(@PathVariable("status") String status);

    @GetMapping("/product/selectProductByProductIdentificationList")
    public R<?> selectProductByProductIdentificationList(@RequestParam("productIdentificationList") List<String> productIdentificationList);

}
