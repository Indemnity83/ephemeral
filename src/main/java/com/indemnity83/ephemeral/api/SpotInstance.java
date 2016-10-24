// Minecraft Elastic Servers
// Copyright (c) 2016.
//
//  This library is free software; you can redistribute it and/or
//  modify it under the terms of the GNU Lesser General Public
//  License as published by the Free Software Foundation version 2.1
//  of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

package com.indemnity83.ephemeral.api;

import com.amazonaws.services.ec2.model.LaunchSpecification;
import com.amazonaws.services.ec2.model.RequestSpotInstancesRequest;
import com.amazonaws.services.ec2.model.RequestSpotInstancesResult;
import com.amazonaws.services.ec2.model.SpotInstanceRequest;
import com.indemnity83.ephemeral.Ephemeral;

import java.util.ArrayList;
import java.util.List;

public class SpotInstance {

    // Inputs
    private String spotPrice;
    private String instanceImage;
    private String instanceType;
    private int instanceCount;
    private ArrayList<String> securityGroups = new ArrayList<String>();

    // Specifications
    private RequestSpotInstancesRequest requestSpec = new RequestSpotInstancesRequest();
    private LaunchSpecification launchSpec = new LaunchSpecification();

    // Outcome
    private ArrayList<String> requestIds = new ArrayList<String>();

    public SpotInstance() {
        this(
                Ephemeral.getDefaultBidPrice(),
                Ephemeral.getDefaultInstanceImage(),
                Ephemeral.getDefaultInstanceType(),
                1,
                new ArrayList<String>()
        );
    }

    public SpotInstance(SecurityGroup securityGroup) {
        this();
        this.addSecurityGroup(securityGroup.toString());
    }

    public SpotInstance(String spotPrice, String instanceImage, String instanceType, int instanceCount, ArrayList<String> securityGroups) {
        setSpotPrice(spotPrice);
        setInstanceImage(instanceImage);
        setInstanceType(instanceType);
        setInstanceCount(instanceCount);
        setSecurityGroups(securityGroups);

        getRequestSpec().setLaunchSpecification(getLaunchSpec());
    }

    public SpotInstance request() {
        System.out.println("Requesting: " + requestSpec);

        RequestSpotInstancesResult requestResult = Ephemeral.ec2.requestSpotInstances(requestSpec);
        List<SpotInstanceRequest> requestResponses = requestResult.getSpotInstanceRequests();

        for (SpotInstanceRequest requestResponse : requestResponses) {
            System.out.println("Created Spot Request: "+requestResponse.getSpotInstanceRequestId());
            requestIds.add(requestResponse.getSpotInstanceRequestId());
        }

        return this;
    }

    public String getSpotPrice() {
        return spotPrice;
    }

    public void setSpotPrice(String spotPrice) {
        this.spotPrice = spotPrice;
        requestSpec.setSpotPrice(this.spotPrice);
    }

    public String getInstanceImage() {
        return instanceImage;
    }

    public void setInstanceImage(String instanceImage) {
        this.instanceImage = instanceImage;
        launchSpec.setImageId(this.instanceImage);
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
        launchSpec.setInstanceType(this.instanceType);
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    public void setInstanceCount(int instanceCount) {
        this.instanceCount = instanceCount;
        requestSpec.setInstanceCount(this.instanceCount);
    }

    public ArrayList<String> getSecurityGroups() {
        return securityGroups;
    }

    public void setSecurityGroups(ArrayList<String> securityGroups) {
        this.securityGroups = securityGroups;
        launchSpec.setSecurityGroups(this.securityGroups);

    }

    public SpotInstance addSecurityGroup(String securityGroup) {
        this.securityGroups.add(securityGroup);
        setSecurityGroups(this.securityGroups);

        return this;
    }

    public ArrayList<String> getRequestIds() {
        return requestIds;
    }

    public RequestSpotInstancesRequest getRequestSpec() {
        return requestSpec;
    }

    public void setRequestSpec(RequestSpotInstancesRequest requestSpec) {
        this.requestSpec = requestSpec;
    }

    public LaunchSpecification getLaunchSpec() {
        return launchSpec;
    }

    public void setLaunchSpec(LaunchSpecification launchSpec) {
        this.launchSpec = launchSpec;
    }


    public SpotInstance in(SecurityGroup securityGroup) {
        addSecurityGroup(securityGroup.toString());

        return this;
    }
}
