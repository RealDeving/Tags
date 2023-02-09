package rip.real.tags.utils.menu.pagination;

import lombok.AllArgsConstructor;
import rip.real.tags.utils.ItemBuilder;
import rip.real.tags.utils.menu.Button;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

@AllArgsConstructor
public class PageButton extends Button {

	private final int mod;
	private final PaginatedMenu menu;

	@Override
	public ItemStack getButtonItem(Player player) {
		if (this.mod > 0) {
			if (hasNext(player)) {
				return new ItemBuilder(Material.REDSTONE_TORCH)
						.name(ChatColor.GREEN + "Next Page")
						.setLore(Arrays.asList(
								ChatColor.YELLOW + "Click here to jump",
								ChatColor.YELLOW + "to the next page."
						))
						.build();
			} else {
				return new ItemBuilder(Material.LEVER)
						.name(ChatColor.GRAY + "Next Page")
						.setLore(Arrays.asList(
								ChatColor.YELLOW + "There is no available",
								ChatColor.YELLOW + "next page."
						))
						.build();
			}
		} else {
			if (hasPrevious()) {
				return new ItemBuilder(Material.REDSTONE_TORCH)
						.name(ChatColor.GREEN + "Previous Page")
						.setLore(Arrays.asList(
								ChatColor.YELLOW + "Click here to jump",
								ChatColor.YELLOW + "to the previous page."
						))
						.build();
			} else {
				return new ItemBuilder(Material.LEVER)
						.name(ChatColor.GRAY + "Previous Page")
						.setLore(Arrays.asList(
								ChatColor.YELLOW + "There is no available",
								ChatColor.YELLOW + "previous page."
						))
						.build();
			}
		}
	}

	@Override
	public void clicked(Player player, ClickType clickType) {
		if (this.mod > 0) {
			if (hasNext(player)) {
				this.menu.modPage(player, this.mod);
				Button.playNeutral(player);
			} else {
				Button.playFail(player);
			}
		} else {
			if (hasPrevious()) {
				this.menu.modPage(player, this.mod);
				Button.playNeutral(player);
			} else {
				Button.playFail(player);
			}
		}
	}

	private boolean hasNext(Player player) {
		int pg = this.menu.getPage() + this.mod;
		return this.menu.getPages(player) >= pg;
	}

	private boolean hasPrevious() {
		int pg = this.menu.getPage() + this.mod;
		return pg > 0;
	}

}
