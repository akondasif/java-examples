/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.devcircus.patterns.examples.creational.builder.example_02.model.items;

import es.devcircus.patterns.examples.creational.builder.example_02.model.packings.Packing;

/**
 *
 * @author adesis
 */
public interface Item {

    public String name();

    public Packing packing();

    public float price();
}
