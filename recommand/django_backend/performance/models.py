from django.db import models


class Performance(models.Model):
    # id = models.AutoField(primary_key=True)
    last_season = models.ForeignKey(
        'season.Season', related_name="last_season", on_delete=models.CASCADE)
    performance_name = models.CharField(max_length=255)
    performance_image = models.CharField(max_length=255)
    performance_type = models.IntegerField()
    create_date = models.DateTimeField(auto_now_add=True)
    update_date = models.DateTimeField(auto_now=True)

    class Meta:
        managed = False
        db_table = 'performance'
