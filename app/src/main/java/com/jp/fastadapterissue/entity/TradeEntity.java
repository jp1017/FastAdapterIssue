/*
******************* Copyright (c) ***********************\
**
**         (c) Copyright 2015, 蒋朋, china
**                  All Rights Reserved
**
**                       _oo0oo_
**                      o8888888o
**                      88" . "88
**                      (| -_- |)
**                      0\  =  /0
**                    ___/`---'\___
**                  .' \\|     |// '.
**                 / \\|||  :  |||// \
**                / _||||| -:- |||||- \
**               |   | \\\  -  /// |   |
**               | \_|  ''\---/''  |_/ |
**               \  .-\__  '-'  ___/-. /
**             ___'. .'  /--.--\  `. .'___
**          ."" '<  `.___\_<|>_/___.' >' "".
**         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
**         \  \ `_.   \_ __\ /__ _/   .-` /  /
**     =====`-.____`.___ \_____/___.-`___.-'=====
**                       `=---='
**
**
**     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
**
**               佛祖保佑         永无BUG
**
**
**                   南无本师释迦牟尼佛
**

**-----------------------版本信息------------------------
** 版    本: V0.1
**
******************** End of Head **********************\
*/

package com.jp.fastadapterissue.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;



/**
 * 文 件 名: TradeItem
 * 创 建 人: 蒋朋
 * 创建日期: 16-9-5 12:41
 * 邮    箱: jp19891017@gmail.com
 * 博    客: https://jp1017.github.io/
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */

@Entity
public class TradeEntity {
    @Id
    private Long _id;
    public String item;

    @Generated(hash = 273989011)
    public TradeEntity(Long _id, String item) {
        this._id = _id;
        this.item = item;
    }

    @Generated(hash = 825917590)
    public TradeEntity() {
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

}


