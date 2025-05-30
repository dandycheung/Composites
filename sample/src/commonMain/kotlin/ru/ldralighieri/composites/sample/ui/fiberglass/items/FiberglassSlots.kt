/*
 * Copyright 2023 Vladimir Raupov
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

package ru.ldralighieri.composites.sample.ui.fiberglass.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.ldralighieri.composites.fiberglass.model.FiberglassLazyItemSlot
import ru.ldralighieri.composites.fiberglass.model.FiberglassRowItemSlot
import ru.ldralighieri.composites.fiberglass.model.FiberglassStickyHeaderSlot
import ru.ldralighieri.composites.fiberglass.row.FiberglassFlowRow
import ru.ldralighieri.composites.fiberglass.row.FiberglassLazyRow
import ru.ldralighieri.composites.sample.ui.theme.AppTheme

internal fun stickyHeaderSlot(): FiberglassStickyHeaderSlot =
    {
        Text(
            text = (it as StickyHeaderItem).title,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = AppTheme.colors.background)
                .padding(top = 16.dp)
                .padding(horizontal = AppTheme.dimensions.horizontalGuideline),
            color = AppTheme.colors.onBackground,
            style = AppTheme.typography.headlineMedium,
        )
    }

internal fun spacerItemSlot(): FiberglassLazyItemSlot = { _, item ->
    Spacer(modifier = Modifier.height((item as SpacerItem).height.dp))
}

internal fun loremIpsumSlot(): FiberglassLazyItemSlot = { _, item ->
    Text(
        text = (item as LoremIpsumItem).text,
        modifier = Modifier.padding(horizontal = AppTheme.dimensions.horizontalGuideline),
        color = AppTheme.colors.onBackground,
        style = AppTheme.typography.bodyMedium,
    )
}

@Composable
private fun ImageSlot(width: Dp) {
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .size(width = width, height = 160.dp)
            .background(color = AppTheme.colors.secondaryContainer, shape = AppTheme.shapes.medium),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            imageVector = Icons.Default.Image,
            contentDescription = "",
            modifier = Modifier.size(64.dp),
            colorFilter = ColorFilter.tint(color = AppTheme.colors.onBackground),
        )
    }
}

internal fun smallImageSlot(): FiberglassLazyItemSlot = { _, _ -> ImageSlot(width = 160.dp) }

internal fun bigImageSlot(): FiberglassLazyItemSlot = { _, _ -> ImageSlot(width = 256.dp) }

internal fun imagesRowSlot(): FiberglassLazyItemSlot = { _, item ->
    with(item as ImagesRowItem) {
        FiberglassLazyRow(
            items = images,
            itemSlots = mapOf(
                SmallImageItem::class to smallImageSlot(),
                BigImageItem::class to bigImageSlot(),
            ),
            contentPadding = PaddingValues(horizontal = AppTheme.dimensions.horizontalGuideline),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
private fun tagSlot(backgroundColor: Color): FiberglassRowItemSlot = { _, item ->
    Chip(
        onClick = {},
        colors = ChipDefaults.chipColors(backgroundColor = backgroundColor),
    ) {
        Text(
            text = (item as TagItem).text,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = AppTheme.colors.contentColorFor(backgroundColor),
            style = AppTheme.typography.labelSmall,
        )
    }
}

@Composable
internal fun secondaryTagSlot(): FiberglassRowItemSlot = tagSlot(AppTheme.colors.secondaryContainer)

@Composable
internal fun tertiaryTagSlot(): FiberglassRowItemSlot = tagSlot(AppTheme.colors.tertiaryContainer)

internal fun tagsFlowRowSlot(): FiberglassLazyItemSlot = { _, item ->
    with(item as TagsFlowRowItem) {
        FiberglassFlowRow(
            items = tags,
            itemSlots = mapOf(
                SecondaryTagItem::class to secondaryTagSlot(),
                TertiaryTagItem::class to tertiaryTagSlot(),
            ),
            contentPadding = PaddingValues(horizontal = AppTheme.dimensions.horizontalGuideline),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        )
    }
}
