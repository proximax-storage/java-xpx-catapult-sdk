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

package io.proximax.sdk;

import java.util.List;

import io.proximax.sdk.infrastructure.QueryParams;
import io.proximax.sdk.model.mosaic.MosaicId;
import io.proximax.sdk.model.mosaic.MosaicInfo;
import io.proximax.sdk.model.mosaic.MosaicLevyInfo;
import io.proximax.sdk.model.mosaic.MosaicNames;
import io.proximax.sdk.model.mosaic.MosaicRichList;
import io.reactivex.Observable;

/**
 * Mosaic interface repository.
 *
 * @since 1.0
 */
public interface MosaicRepository {

    /**
     * <p>Gets a MosaicInfo for a given mosaicId</p>
     * <p>GET /mosaic/{hexMosaicId}
     * 
     * @param mosaicId BigInteger
     * @return Observable of {@link MosaicInfo}
     */
    Observable<MosaicInfo> getMosaic(MosaicId mosaicId);

    /**
     * <p>Gets MosaicInfo for different mosaicIds.</p>
     * <p>POST /mosaic</p>
     *
     * @param mosaicIds List of BigInteger
     * @return Observable of {@link MosaicInfo} list
     */
    Observable<List<MosaicInfo>> getMosaics(List<MosaicId> mosaicIds);

    /**
     * <p>Gets list of MosaicName for different mosaicIds.</p>
     * <p>POST /mosaic/names</p>
     * 
     * @param mosaicIds List of BigInteger
     * @return Observable of {@link MosaicNames} list
     */
    Observable<List<MosaicNames>> getMosaicNames(List<MosaicId> mosaicIds);

    /**
     * <p>
     * Gets list of MosaicRichList.
     * </p>
     * 
     * @param MosaicRichListDTO List of MosaicRichList
     * @return Observable of {@link MosaicRichList} list
     */
    Observable<List<MosaicRichList>> getMosaicRichList(MosaicId mosaicId);
    
    /**
     * <p>
     * Gets list of MosaicRichList.
     * </p>
     * 
     * @param MosaicRichListDTO List of MosaicRichList
     * @param queryParams    QueryParams
     * @return Observable of {@link MosaicRichList} list
     */
    Observable<List<MosaicRichList>> getMosaicRichList(MosaicId mosaicId, QueryParams queryParams);

    /**
     * <p>
     * Gets mosaic levy info for mosaicId.
     * </p>
     * 
     * @param mosaicId Mosaic Id
     * @return Observable of {@link MosaicLevyInfo}
     */
    Observable<MosaicLevyInfo> getMosaicLevyInfo(MosaicId mosaicId);
}
