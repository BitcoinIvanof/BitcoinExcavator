/**
 * Project BitcoinExcavator.
 * Copyright Michał Szczygieł & Aleksander Śmierciak
 * Created at June 9, 2014.
 */
package com.bitcoin.core;

import java.net.Proxy;
import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import com.bitcoin.core.network.NetworkState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bitcoin.util.BitcoinOptions;

/**
 * Main core class for dig bitcoins. Responsible for start devices to found
 * collision for bitcoins hash.
 *
 * @author m4gik <michal.szczygiel@wp.pl>
 */
public class BitcoinExcavator implements Excavator {

    /**
     * Logger for monitoring runtime.
     */
    private static final Logger log = LoggerFactory
            .getLogger(BitcoinExcavator.class);

    /**
     * Returns actual time.
     *
     * @return the actual time.
     */
    public static String dateTime() {
        return "["
                + DateFormat.getDateTimeInstance(DateFormat.SHORT,
                DateFormat.MEDIUM).format(new Date()) + "]";
    }

    private AtomicLong attempts = new AtomicLong(0);

    private AtomicLong blocks = new AtomicLong(0);

    private Set<String> enabledDevices = null;

    private AtomicLong hashCount = new AtomicLong(0);

    private AtomicLong hwErrors = new AtomicLong(0);

    private AtomicLong rejects = new AtomicLong(0);

    private AtomicBoolean running = new AtomicBoolean(true);

    private BitcoinOptions bitcoinOptions = null;

    public BitcoinExcavator(BitcoinOptions bitcoinOptions) {
        this.bitcoinOptions = bitcoinOptions;
    }

    /**
     * List of threads.
     */
    List<Thread> threads = new ArrayList<Thread>();

    public Long addAndGetHashCount(Long delta) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * This method adds thread on running process.
     *
     * @param thread The thread to add.
     */
    public void addThread(Thread thread) {
        // TODO Auto-generated method stub

    }

    /**
     * Display error message what happen and interrupt the action.
     *
     * @param reason Message with error.
     * @return Formatted error to display.
     */
    public String error(String reason) {
        log.error(reason);
        threads.get(0).interrupt();

        return dateTime() + " error: " + reason;
    }

    /**
     * This method runs process for dig coins.
     */
    public void execute() {
        log.info("Bitcoin Excavator process started");

        ArrayList<NetworkState> networkStates = BitcoinOptions
                .networkConfiguration(bitcoinOptions, this);
        StringBuilder list = new StringBuilder();

        for (int i = 0; i < networkStates.size(); i++) {
            log.info("user: " + networkStates.get(i).getUser() + " url: "
                    + networkStates.get(i)
                    .getQueryUrl());

            list.append(networkStates.get(i).getQueryUrl().toString());

            if (i >= 1 && i < networkStates.size() - 1) {
                list.append(", ");
            }
        }

        log.info("Connecting to: " + list);

        //TODO make implementation for GPU

    }

    /**
     * Gets current time from system.
     *
     * @return The time in millisecnonds.
     */
    public Long getCurrentTime() {
        return System.nanoTime() / 1000000L;
    }

    /**
     * Gets information about running status.
     *
     * @return True if process is running, false if is not.
     */
    public Boolean getRunning() {
        return running.get();
    }

    /**
     * Method stops digging, and close all running process.
     */
    public void halt() {
        running.set(false);

        for(int i = 0; i <getThreads().size(); i++) {
            Thread thread = getThreads().get(i);

            if(thread != Thread.currentThread()) {
                thread.interrupt();
            }
        }
    }

    public Long incrementAttempts() {
        // TODO Auto-generated method stub
        return null;
    }

    public Long incrementBlocks() {
        // TODO Auto-generated method stub
        return null;
    }

    public Long incrementHWErrors() {
        // TODO Auto-generated method stub
        return null;
    }

    public Long incrementRejects() {
        // TODO Auto-generated method stub
        return null;
    }

    public void info(String information) {
        // TODO Auto-generated method stub

    }

    public AtomicLong getAttempts() {
        return attempts;
    }

    public AtomicLong getBlocks() {
        return blocks;
    }

    public Set<String> getEnabledDevices() {
        return enabledDevices;
    }

    public AtomicLong getHashCount() {
        return hashCount;
    }

    public AtomicLong getHwErrors() {
        return hwErrors;
    }

    public AtomicLong getRejects() {
        return rejects;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    /**
     * Gets options for {@link com.bitcoin.core.Excavator} class.
     *
     * @return The instance of {@link com.bitcoin.util.BitcoinOptions}.
     */
    public BitcoinOptions getBitcoinOptions() {
        return bitcoinOptions;
    }

    public void setRejects(AtomicLong rejects) {
        this.rejects = rejects;
    }

    public void setAttempts(AtomicLong attempts) {
        this.attempts = attempts;
    }

    public void setBlocks(AtomicLong blocks) {
        this.blocks = blocks;
    }

    public void setEnabledDevices(Set<String> enabledDevices) {
        this.enabledDevices = enabledDevices;
    }

    public void setHashCount(AtomicLong hashCount) {
        this.hashCount = hashCount;
    }

    public void setHwErrors(AtomicLong hwErrors) {
        this.hwErrors = hwErrors;
    }

    public void setBitcoinOptions(BitcoinOptions bitcoinOptions) {
        this.bitcoinOptions = bitcoinOptions;
    }
}
