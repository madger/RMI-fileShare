package com.fileshare.time;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Mateusz Kwiecien
 *         Date: 5/30/13
 */

public class Clock implements IClock {
    private Map<Integer, Integer> vector = new HashMap<Integer, Integer>();
    private int nodeId;

    public Clock(Integer id) {
        this.nodeId = id;
        this.vector.put(id, 0);
    }

    @Override
    public int getNodeId() {
        return nodeId;
    }

    @Override
    public void setNodeId(int clientId) {
        this.nodeId = clientId;
    }

    @Override
    public void addNode(Integer id, Integer state) {
        if (!vector.containsKey(id)) {
            vector.put(id, state);
        }
    }

    @Override
    public Map<Integer, Integer> getVector() {
        return vector;
    }

    @Override
    public void setVector(Map<Integer, Integer> vector) {
        this.vector = vector;
    }

    @Override
    public int getSize() {
        return vector.size();
    }

    @Override
    public void incrementClock() {
        if (vector.containsKey(this.nodeId)) {
            vector.put(this.nodeId, (vector.get(this.nodeId) + 1));
        }
    }

    @Override
    public boolean equivalent(Clock current) {
        return (isGreater(current) != true && this.isLower(current) != true);
    }

    @Override
    public boolean isGreater(Clock current) {
        if (current.equals(this)) {
            return false;
        } else {
            Iterator it = current.vector.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                Integer get = this.vector.get(((Integer) pairs.getKey()));
                if (!((Integer) pairs.getValue() < get)) {
                    return false;
                }
                it.remove();
            }

            return true;
        }

    }

    @Override
    public boolean isLower(Clock current) {
        if (current.equals(this)) {
            return false;
        } else {
            Iterator it = current.vector.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                Integer get = this.vector.get(((Integer) pairs.getKey()));
                if (!((Integer) pairs.getValue() > get)) {
                    return false;
                }
                it.remove();
            }

            return true;
        }

    }

    @Override
    public boolean equals(Object obj) {

        if (super.equals(obj)) {
            return true;
        }

        Clock current = (Clock) obj;

        if (current.vector.size() == this.vector.size()) {

            Iterator it = current.vector.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                Integer get = this.vector.get(((Integer) pairs.getKey()));
                if (pairs.getValue() != get) {
                    return false;
                }
                it.remove();
            }
            return true;
        } else {
            return false;
        }

    }
}

