package com.duycs.template.entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int settingId;

    private string key;
    private string value;
}