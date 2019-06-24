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

package io.proximax.sdk.model.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.proximax.core.utils.Base32Encoder;
import io.proximax.core.utils.HexEncoder;
import io.proximax.sdk.gen.model.AccountDTO;
import io.proximax.sdk.gen.model.AccountInfoDTO;
import io.proximax.sdk.gen.model.MosaicDTO;
import io.proximax.sdk.gen.model.UInt64DTO;
import io.proximax.sdk.model.blockchain.NetworkType;
import io.proximax.sdk.model.mosaic.Mosaic;
import io.proximax.sdk.model.mosaic.NetworkCurrencyMosaic;

class AccountInfoTest {

    @Test
    void shouldCreateAccountInfoViaConstructor() {
        List<Mosaic> mosaics =  Arrays.asList(NetworkCurrencyMosaic.createRelative(BigInteger.valueOf(10)));
        AccountInfo accountInfo = new AccountInfo(Address.createFromRawAddress("SDGLFWDSHILTIUHGIBH5UGX2VYF5VNJEKCCDBR26"),
                new BigInteger("964"),
                "cf893ffcc47c33e7f68ab1db56365c156b0736824a0c1e273f9e00b8df8f01eb",
                new BigInteger("966"),
                mosaics);

        assertEquals(Address.createFromRawAddress("SDGLFWDSHILTIUHGIBH5UGX2VYF5VNJEKCCDBR26"), accountInfo.getAddress());
        assertEquals(new BigInteger("964"), accountInfo.getAddressHeight());
        assertEquals("cf893ffcc47c33e7f68ab1db56365c156b0736824a0c1e273f9e00b8df8f01eb", accountInfo.getPublicKey());
        assertEquals(new BigInteger("966"), accountInfo.getPublicKeyHeight());
        assertEquals(mosaics, accountInfo.getMosaics());
        assertEquals(PublicAccount.createFromPublicKey("cf893ffcc47c33e7f68ab1db56365c156b0736824a0c1e273f9e00b8df8f01eb", NetworkType.MIJIN_TEST), accountInfo.getPublicAccount());

    }
    
    @Test
    void fromDto() {
       UInt64DTO uint = new UInt64DTO();
       uint.add(10l);
       uint.add(0l);

       MosaicDTO mosaicDto = new MosaicDTO();
       mosaicDto.setAmount(uint);
       mosaicDto.setId(uint);

       AccountDTO accountDto = new AccountDTO();
       accountDto.setAddress("cf893ffcc47c33e7f68ab1db56365c156b0736824a0c1e273f9e00b8df8f01eb");
       accountDto.setAddressHeight(uint);
       accountDto.setPublicKeyHeight(uint);
       accountDto.setMosaics(Arrays.asList(mosaicDto));

       AccountInfoDTO dto = new AccountInfoDTO();
       dto.setAccount(accountDto);

       AccountInfo accountInfo = AccountInfo.fromDto(dto);
       // assertions
       assertEquals("cf893ffcc47c33e7f68ab1db56365c156b0736824a0c1e273f9e00b8df8f01eb", HexEncoder.getString(Base32Encoder.getBytes(accountInfo.getAddress().plain())));
       assertEquals(BigInteger.TEN, accountInfo.getAddressHeight());
       assertEquals(BigInteger.TEN, accountInfo.getPublicKeyHeight());
       assertEquals(1, accountInfo.getMosaics().size());
       assertEquals(BigInteger.TEN, accountInfo.getMosaics().get(0).getAmount());
       assertEquals(BigInteger.TEN, accountInfo.getMosaics().get(0).getId().getId());
    }
}