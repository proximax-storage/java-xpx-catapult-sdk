/*
 * Copyright 2018 NEM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.sdk.model.mosaic;

import static io.proximax.sdk.utils.dto.UInt64Utils.toBigInt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import io.proximax.sdk.gen.model.MosaicInfoDTO;
import io.proximax.sdk.model.account.PublicAccount;
import io.proximax.sdk.model.network.NetworkType;

/**
 * The mosaic info structure contains its properties, the owner and the namespace to which it belongs to.
 */
public class MosaicInfo {
    private final String metaId;
    private final MosaicId mosaicId;
    private final BigInteger supply;
    private final BigInteger height;
    private final PublicAccount owner;
    private final MosaicProperties properties;

    public MosaicInfo(String metaId, MosaicId mosaicId, BigInteger supply, BigInteger height, PublicAccount owner, MosaicProperties properties) {
        this.metaId = metaId;
        this.mosaicId = mosaicId;
        this.supply = supply;
        this.height = height;
        this.owner = owner;
        this.properties = properties;
    }

    public String getMetaId() {
        return metaId;
    }

    /**
     * Returns the mosaic id
     *
     * @return mosaic id
     */
    public MosaicId getMosaicId() {
        return mosaicId;
    }

    /**
     * Returns the total mosaic supply
     *
     * @return total mosaic supply
     */
    public BigInteger getSupply() {
        return supply;
    }

    /**
     * Returns the block height it was created
     *
     * @return height it was created
     */
    public BigInteger getHeight() {
        return height;
    }

    /**
     * Returns the mosaic account owner
     *
     * @return mosaic account owner
     */
    public PublicAccount getOwner() {
        return owner;
    }

    /**
     * Returns true if the supply is mutable
     *
     * @return if supply is mutable
     */
    public boolean isSupplyMutable() {
        return properties.isSupplyMutable();
    }

    /**
     * Returns tue if the mosaic is transferable between non-owner accounts
     *
     * @return if the mosaic is transferable between non-owner accounts
     */
    public boolean isTransferable() {
        return properties.isTransferable();
    }

    /**
     * Return the number of blocks from height it will be active
     *
     * @return the optional number of blocks from height it will be active
     */
    public Optional<BigInteger> getDuration() {
        return properties.getDuration();
    }

    /**
     * Returns the mosaic divisibility
     *
     * @return mosaic divisibility
     */
    public int getDivisibility() {
        return properties.getDivisibility();
    }

	@Override
	public String toString() {
		return "MosaicInfo [metaId=" + metaId + ", mosaicId=" + mosaicId + ", supply=" + supply + ", height=" + height
				+ ", owner=" + owner + ", properties=" + properties + "]";
	}
	
	/**
	 * create mosaic info instance from the DTO
	 * 
	 * @param dto mosaic info DTO
	 * @param networkType network type
	 * @return mosaic info
	 */
	public static MosaicInfo fromDto(MosaicInfoDTO dto, NetworkType networkType) {
	   return new MosaicInfo(
	         dto.getMeta().getId(),
            new MosaicId(toBigInt(new ArrayList<>(dto.getMosaic().getMosaicId()))),
            toBigInt(new ArrayList<>(dto.getMosaic().getSupply())),
            toBigInt(new ArrayList<>(dto.getMosaic().getHeight())),
            new PublicAccount(dto.getMosaic().getOwner(), networkType),
            MosaicProperties.fromDto(dto.getMosaic().getProperties())
      );
	}
}

